package de.tud.fcds.friendly

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

@RunWith(Parameterized)
class NumberCalculatorFactorizationTest {

    NumberCalculator calculator

    def number
    def factors

    NumberCalculatorFactorizationTest(def number, def factors) {
        this.number = number
        this.factors = factors
        calculator = new NumberCalculator()
    }

    @Test void factorization() {
        assert calculator.factorsOf(number) == factors
    }

    @Parameters static Collection<Object[]> data() {
        [   [1, [1]],
            [2, [1, 2]],
            [3, [1, 3]],
            [4, [1, 2, 4]],
            [5, [1, 5]],
            [6, [1, 2, 3, 6]],
            [7, [1, 7]],
            [8, [1, 2, 4, 8]],
            [9, [1, 3, 9]],
            [10, [1, 2, 5, 10]],
            [11, [1, 11]],
            [12, [1, 2, 3, 4, 6, 12]],
            [13, [1, 13]],
            [14, [1, 2, 7, 14]],
            [15, [1, 3, 5, 15]],
            [16, [1, 2, 4, 8, 16]],
            [17, [1, 17]],
            [18, [1, 2, 3, 6, 9, 18]],
            [19, [1, 19]],
            [20, [1, 2, 4, 5, 10, 20]],
            [21, [1, 3, 7, 21]],
            [22, [1, 2, 11, 22]],
            [23, [1, 23]],
            [24, [1, 2, 3, 4, 6, 8, 12, 24]],
            [25, [1, 5, 25]],
            [26, [1, 2, 13, 26]],
            [27, [1, 3, 9, 27]],
            [28, [1, 2, 4, 7, 14, 28]],
            [29, [1, 29]],
            [30, [1, 2, 3, 5, 6, 10, 15, 30]],
        ].collect { it as Object[] }
    }

}
