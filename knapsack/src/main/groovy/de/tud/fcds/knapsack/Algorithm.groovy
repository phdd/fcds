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
        items = map table.drop(0) using {
            [ value:  getAt(0) as Integer,
              weight: getAt(1) as Integer ] as Item
        }

        items.sort()

        withPool poolSize, {

        }
    }

}
