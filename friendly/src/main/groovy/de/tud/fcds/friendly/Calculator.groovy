package de.tud.fcds.friendly

import groovyx.gpars.actor.DefaultActor

import static groovyx.gpars.GParsPool.withPool
import static java.lang.Math.sqrt

class Calculator extends DefaultActor {

    int size

    @Override void act() {
        loop {
            react {
                reply fractionsWithin(it as IntRange)
            }
        }
    }

    def fractionsWithin(IntRange range) {
        withPool(size, {
            range.collectParallel { fractionFor it }
        })
    }

    def fractionFor(int number) {
        [   numerator: factorsOf(number).sum(),
            denominator: number
        ] as Fraction
    }

    def factorsOf(Integer target) {
        if (target == 1) return [1]
        if (target < 4)  return [1, target]

        def targetSqrt = sqrt target
        def lowFactors = (2..targetSqrt).grep { (target % it) == 0 }

        if (lowFactors == []) return [1, target]
        def half = lowFactors.size() - ((lowFactors[-1] == targetSqrt) ? 1 : 0)

        [1] + lowFactors + (0..<half).collect { target.intdiv(lowFactors[it]) }.reverse() + [target]
    }

}