package de.tud.fcds.friendly

import groovy.transform.CompileStatic
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
                findFriendsParallel it.fractions
                if (--jobs < 1) stop();
            }
        }
    }

    @Override void handleTermination() {
        calculator.stop()
        super.handleTermination()
    }

    def findFriendsParallel(List list) {
        withPool {
            List friends = []

            list.eachWithIndexParallel { Fraction fraction, int index ->
                friends.addAll findFriends(list, index, fraction)
            }

            friendsFoundCallback friends
        }
    }

    @CompileStatic
    def findFriends(List list, int index, Fraction fraction) {
        List friends = []

        (index..<list.size()).each { int it ->
            Fraction item = list[it] as Fraction
            if (item.numerator != fraction.numerator && fraction.isEqualTo(item)) {
                friends << [item.denominator, fraction.denominator]
            }
        }

        friends
    }

}
