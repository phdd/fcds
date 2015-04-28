package de.tud.fcds.bucketsort

import de.tud.fcds.bucketsort.entity.Worker
import groovyx.gpars.actor.DefaultActor

class Algorithm extends DefaultActor {

    long items
    File target
    Scanner scanner
    List<Worker> workers

    Algorithm(File input, File output) {
        this(input, output, Runtime.getRuntime().availableProcessors(), 100)
    }

    Algorithm(File input, File output, long workerCount) {
        this(input, output, workerCount, 100)
    }

    Algorithm(File input, File output, long workerCount, long bucketCount) {
        scanner = new Scanner(input)
        items = firstLineOf input toLong()
        target = output
        workers = [0..workerCount].collect { new Worker().start() }
    }

    String firstLineOf(File file) {
        new BufferedReader(new FileReader(file)).readLine()
    }

    def sort() {

        while (scanner.hasNextLine()) {

        }
    }

}
