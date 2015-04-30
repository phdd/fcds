package de.tud.fcds.friendly

import groovyx.gpars.actor.Actor
import groovyx.gpars.actor.DefaultActor

import static groovyx.gpars.GParsPool.withPool

class Algorithm extends DefaultActor {

    int start
    int end
    int size

    Actor calculator
    Closure callback

    @Override void handleStart() {
        super.handleStart()
        calculator.send start..end
        calculator.stop()
    }

    @Override void act() {
        react {
            if (it instanceof List)
                findAllFriendsWithin it
        }
    }

    def findAllFriendsWithin(List list) {
        callback withPool(size, {
            list.collectParallel { fraction ->

                    list.findAll { it == fraction }
                        .collect { it.denominator }

            }.findAllParallel { it.size > 1 }
             .unique()
        })
    }

}
