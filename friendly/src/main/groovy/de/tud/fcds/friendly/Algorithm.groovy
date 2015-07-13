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

    @Override void handleStart() {
        super.handleStart()

        items = read inputPath using { split ' ' }
        items = map items by { getAt(0).toInteger()..getAt(1).toInteger() }

        items.each {
            if (it.size() > 1)
                calculator.send it
        }
    }

    @Override void act() {
        loop {
            react {
                rangeChangedCallback it.range
                findAllFriendsWithin it.fractions
            }
        }
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
