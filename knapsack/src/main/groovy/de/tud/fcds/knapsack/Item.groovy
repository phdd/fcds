package de.tud.fcds.knapsack

import groovy.transform.Immutable
import groovy.transform.ToString

@ToString(includeNames = true, includePackage = false)
@Immutable class Item {

    BigDecimal profit
    BigDecimal weight

}
