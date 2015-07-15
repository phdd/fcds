package de.tud.fcds.friendly

import groovy.transform.Immutable

@Immutable class Fraction {

    int numerator
    int denominator

    double ratio

    private Fraction reduced

    Fraction reduced() {
        if (reduced == null)
            reduced = reduce this

        reduced
    }

    private def reduce(Fraction fraction) {
        def divisor = gcd fraction.numerator, fraction.denominator
        [   numerator: numerator / divisor,
            denominator: denominator / divisor
        ] as Fraction
    }

    private def gcd(int u, int v) {
        !v ? u : gcd(v, u % v)
    }

    @Override boolean equals(Object obj) {
        switch (obj) {
            case !Fraction: return false
            case is(this): return true
            default: return (obj.numerator / obj.denominator == numerator / denominator)
        }
    }

}
