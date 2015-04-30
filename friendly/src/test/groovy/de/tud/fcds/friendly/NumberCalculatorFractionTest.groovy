package de.tud.fcds.friendly

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.BlockJUnit4ClassRunner

@RunWith(BlockJUnit4ClassRunner)
class NumberCalculatorFractionTest {

    static final Fraction FRACTION_72_30   = [ numerator: 72,  denominator: 30  ]
    static final Fraction FRACTION_336_140 = [ numerator: 336, denominator: 140 ]
    static final Fraction FRACTION_12_5    = [ numerator: 12,  denominator: 5   ]

    NumberCalculator calculator

    @Before void init() {
        calculator = new NumberCalculator()
    }

    @Test void factorization() {
        calculator.with {
            fractionFor(30)  == FRACTION_72_30
            fractionFor(140) == FRACTION_336_140
        }
    }

    @Test void reduction() {
        assert FRACTION_336_140.reduced() == FRACTION_12_5
        assert FRACTION_72_30.reduced()   == FRACTION_12_5
    }

    @Test void equality() {
        assert new Fraction(6, 8) == new Fraction(6, 8)
        assert new Fraction(6, 8) == new Fraction(3, 4)
        assert new Fraction(4, 4) != new Fraction(3, 4)
    }

}
