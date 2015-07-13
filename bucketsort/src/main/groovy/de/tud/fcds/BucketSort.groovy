package de.tud.fcds

import de.tud.fcds.bucketsort.Algorithm

class BucketSort {

    static void main(def args) {
        def cli = new CliBuilder(usage: 'bucketsort')

        cli.with {
            i longOpt: 'input', args: 1, argName: 'path', required: true, 'file of unsorted lines'
            o longOpt: 'output', args: 1, argName: 'path', required: true, 'target file for sorted lines'
        }

        def params = cli.parse(args)

        if (params) {
            new Algorithm(params.i, params.o).sort()
        }
    }

}