package de.tud.fcds.friendly

import groovyx.gpars.actor.Actor
import groovyx.gpars.actor.DefaultActor

import static groovyx.gpars.GParsPool.withPool
import static java.lang.System.currentTimeMillis

class Algorithm extends DefaultActor implements FileAware {

    String inputPath
    def items

    Actor calculator
    Closure friendsFoundCallback
    Closure rangeChangedCallback

    def jobs = 0
    def start

    @Override void handleStart() {
        super.handleStart()

        items = read inputPath using { split ' ' }
        items = map items by { getAt(0).toInteger()..getAt(1).toInteger() }

        items.each {
            if (it.size() > 1) {
                calculator.send it
                jobs++
                start = currentTimeMillis()
            }
        }
    }

    @Override void act() {
        loop {
            react {
                println "calc: ${currentTimeMillis() - start}ms"
                start = currentTimeMillis()

                rangeChangedCallback it.range
                findAllFriendsWithin it.fractions

                println "find: ${currentTimeMillis() - start}ms"

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
        withPool {
            def friends = []

            list.eachWithIndexParallel { fraction, index ->
                (index..<list.size()).each {
                    if (list[it].numerator != fraction.numerator && list[it].equals(fraction)) {
                        friends << [list[it].denominator, fraction.denominator]
                    }
                }
            }

            friendsFoundCallback friends
        }
    }

}
