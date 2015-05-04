package de.tud.fcds.knapsack

import groovy.transform.Immutable
import groovy.transform.ToString

@ToString(includeNames = true, includePackage = false)
@Immutable class Item implements Comparable<Item> {

    BigDecimal profit
    BigDecimal weight

    BigDecimal density() {
        profit / weight
    }

    @Override int compareTo(Item other) {
        density() - other.density()
    }

}
