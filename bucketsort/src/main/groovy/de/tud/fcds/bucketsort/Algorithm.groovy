package de.tud.fcds.bucketsort

import groovyx.gpars.actor.DefaultActor

class Algorithm extends DefaultActor {

    File target
    long items

    def Algorithm(File input, File output) {
        items = firstLineOf input toLong()
        target = output
    }

    String firstLineOf(File file) {
        new BufferedReader(new FileReader(file)).readLine()
    }

    def sort() {
        Runtime.getRuntime().availableProcessors()
    }

}
