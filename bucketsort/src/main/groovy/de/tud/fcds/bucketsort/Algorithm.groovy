package de.tud.fcds.bucketsort

import groovyx.gbench.Benchmark

import static groovyx.gpars.GParsPool.withPool

class Algorithm {

    static final int ASCII_CHAR_COUNT = 128

    List<List> buckets

    File input
    File output

    Algorithm(String inputPath, String outputPath) {
        input = new File(inputPath)
        output = new File(outputPath)
        buckets = (1..ASCII_CHAR_COUNT).collect { [] }
    }

    def sort() {
        inputFileToBuckets()
        sortBucketsParallel()
        bucketsToOutputFile()
    }

    def inputFileToBuckets() {
        def items

        input.eachLine {
            if (items == null) items = it.toLong()
            else toBucket it
        }
    }

    def sortBucketsParallel() {
        withPool {
            buckets.eachParallel {
                it.sort()
            }
        }
    }

    def bucketsToOutputFile() {
        output.withWriter { writer ->
            buckets.each { bucket ->
                bucket.each { line ->
                    writer.write "$line\n"
                }
            }
        }
    }

    def toBucket(String line) {
        def associatedBucket = firstCharacterOf(line) % buckets.size()
        buckets.get(associatedBucket).add line
    }

    int firstCharacterOf(String line) {
        line.charAt(0) as int
    }

}
