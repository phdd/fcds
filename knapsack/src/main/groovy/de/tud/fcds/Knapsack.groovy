package de.tud.fcds

import de.tud.fcds.knapsack.Algorithm

class Knapsack {

    static void main(def args) {
        def cli = new CliBuilder(usage: 'knapsack')

        cli.with {
            i longOpt: 'input', args: 1, argName: 'path', required: true, 'file of space separated values and weights'
        }

        def params = cli.parse(args)

        if (params) {
            int poolSize = Runtime.runtime.availableProcessors()
            println new Algorithm(inputPath: params.i, poolSize: poolSize).solve()
        }
    }

}
