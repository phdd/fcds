package de.tud.fcds.bucketsort

class BucketSort {

    static final int DEFAULT_BUCKET_COUNT = 100

    static void main(def args) {
        def cli = new CliBuilder(usage: 'java -jar bucketsort.jar')

        cli.with {
            i longOpt: 'input', args: 1, argName: 'path', required: true, 'file of unsorted lines'
            o longOpt: 'output', args: 1, argName: 'path', required: true, 'target file for sorted lines'
            b longOpt: 'buckets', args: 1, argName: 'bucket-count', 'number of buckets'
            t longOpt: 'threads', args: 1, argName: 'thread-count', 'number of threads'
        }

        def params = cli.parse(args)

        if (params)
            new Algorithm(params.i, params.o).sort()
    }

}