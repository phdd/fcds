package de.tud.fcds.knapsack

import groovy.transform.Immutable
import groovy.transform.ToString

@ToString(includeNames = true, includePackage = false)
@Immutable class Item implements Comparable<Item> {

    int value
    int weight

    double density() {
        value / weight
    }

    @Override int compareTo(Item other) {
        density() - other.density()
    }

}
