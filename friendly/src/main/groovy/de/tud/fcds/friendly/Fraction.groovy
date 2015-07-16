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

    @Override boolean equals(Object obj) {
        switch (obj) {
            case !Fraction: return false
            case is(this): return true
            default: return (obj.reduced.denominator == reduced.denominator
                          && obj.reduced.numerator   == reduced.numerator)
        }
    }

}
