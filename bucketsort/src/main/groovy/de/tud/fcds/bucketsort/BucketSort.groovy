package de.tud.fcds.bucketsort

class BucketSort {

    BucketSort(String inputPath, String outputPath) {
        assert inputPath != null
        assert outputPath != null

        def inputFile = new File(inputPath)
        def outputFile = new File(outputPath)

        assert inputFile.canRead()
        outputFile.createNewFile();

        def algorithm = new Algorithm(inputFile, outputFile)

        algorithm.sort()
    }

    static void main(def args) {
        def cli = new CliBuilder(usage: 'java -jar bucketsort.jar')

        cli.with {
            i longOpt: 'input', args: 1, argName: 'path', required: true, 'file of unsorted lines'
            o longOpt: 'output', args: 1, argName: 'path', required: true, 'target file for sorted lines'
        }

        def params = cli.parse(args)
        if (params) new BucketSort(params.i, params.o)
    }

}