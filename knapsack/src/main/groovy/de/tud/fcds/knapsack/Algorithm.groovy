package de.tud.fcds.knapsack

import static groovyx.gpars.GParsPool.withPool

class Algorithm {

    int size
    List<Item> items
    int capacity

    Algorithm(String inputPath, int size) {
        List lines = new File(inputPath).collect { it.split() }
        this.size = size
        capacity = lines.first()[1].toInteger()
        items = lines.drop(0).collect {
            new Item(value: it[0].toInteger(), weight: it[1].toInteger())
        }
    }

    def solve() {
        items.sort()

        withPool(size, {

        })
    }

}
