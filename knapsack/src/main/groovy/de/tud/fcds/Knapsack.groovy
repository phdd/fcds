package de.tud.fcds

import de.tud.fcds.knapsack.Algorithm

class Knapsack {

    static void main(def args) {
        def cli = new CliBuilder(usage: 'knapsack')

        cli.with {
            i longOpt: 'input', args: 1, argName: 'path', required: true, 'file of space separated values and weights'
            p longOpt: 'processors', args: 1, argName: 'number', required: true, 'number of processors to use'
        }

        def params = cli.parse(args)

        if (params) {
            int poolSize = params.p as Integer
            def solution = new Algorithm(inputPath: params.i, poolSize: poolSize).solve()

            solution.each { println "$it.count * $it.item" }
            println "\nTotal Weight: ${solution.collect { it.item.weight * it.count }.sum()}"
            println "Total Profit: ${solution.collect { it.item.profit * it.count }.sum()}"
        }
    }

}
