package de.tud.fcds.friendly

import groovy.transform.Immutable

@Immutable class Fraction {

    int numerator
    int denominator

    Fraction reduced

    static from(numerator, denominator) {
        [   numerator: numerator,
            denominator: denominator,
            reduced: reduce(numerator, denominator)
        ] as Fraction
    }

    private static reduce(numerator, denominator) {
        def divisor = gcd numerator, denominator

        [   numerator: numerator / divisor,
            denominator: denominator / divisor
        ] as Fraction
    }

    private static gcd(int u, int v) {
        !v ? u : gcd(v, u % v)
    }

    def isEqualTo(Fraction fraction) {
        return (fraction.reduced.denominator == reduced.denominator
             && fraction.reduced.numerator   == reduced.numerator)
    }

}
