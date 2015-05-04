package de.tud.fcds.knapsack

import static groovyx.gpars.GParsPool.withPool

class Algorithm implements FileAware {

    def poolSize
    def inputPath

    def capacity
    List items
    List table

    def solve() {
        table = read inputPath using { split ' ' }
        capacity = get row: 0, col: 1 from table
        items = map table.drop(1) by {
            [ profit: getAt(0) as Integer,
              weight: getAt(1) as Integer ] as Item
        }

        withPool poolSize, {
            knapsack items, capacity.toInteger()
        }
    }

    def knapsack(items, BigDecimal capacity) {
        def profit = { list -> list.collect { it.item.profit * it.count }.sum() }

        def n = items.size()
        def wm = capacity.unscaledValue()
        def m = (0..n).collect { i ->
                    (0..wm).collect { w -> [] } }

        (1..wm).eachParallel { weight ->
            (1..n).each { itemIndex ->
                Item item = items[itemIndex - 1]

                def itemWeight = item.weight.unscaledValue()
                def bi = weight.intdiv itemWeight

                m[itemIndex][weight] = (0..bi).collect {
                    count -> m[itemIndex - 1][weight - itemWeight * count] + [[ item: item, count: count ]]

                }.max(profit)
                 .findAll { it.count }
            }
        }

        return m[n][wm]
    }

}
