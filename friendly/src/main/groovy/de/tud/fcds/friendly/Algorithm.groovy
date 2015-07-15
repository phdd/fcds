package de.tud.fcds.friendly

import groovyx.gpars.actor.Actor
import groovyx.gpars.actor.DefaultActor

import static groovyx.gpars.GParsPool.withPool

class Algorithm extends DefaultActor implements FileAware {

    String inputPath
    def items

    Actor calculator
    Closure friendsFoundCallback
    Closure rangeChangedCallback

    def jobs = 0

    @Override void handleStart() {
        super.handleStart()

        items = read inputPath using { split ' ' }
        items = map items by { getAt(0).toInteger()..getAt(1).toInteger() }

        items.each {
            if (it.size() > 1) {
                calculator.send it
                jobs++
            }
        }
    }

    @Override void act() {
        loop {
            react {
                rangeChangedCallback it.range
                findAllFriendsWithin it.fractions

                if (--jobs < 1) {
                    stop();
                }
            }
        }
    }

    @Override void handleTermination() {
        calculator.stop()
        super.handleTermination()
    }

    def findAllFriendsWithin(List list) {
        friendsFoundCallback withPool {
            list.collectParallel { fraction ->

                    list.findAll { it == fraction }
                        .collect { it.denominator }

            }.findAllParallel { it.size > 1 }
             .unique()
        }
    }

}
