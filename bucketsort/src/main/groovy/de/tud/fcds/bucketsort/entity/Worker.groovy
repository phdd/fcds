package de.tud.fcds.bucketsort.entity

import groovyx.gpars.actor.DefaultActor

class Worker extends DefaultActor {

    def process(BucketSpecification bucketSpec) {
        def reader = wrap bucketSpec.container

        reader
                .lines()
                .skip(bucketSpec.offset)
                .limit(bucketSpec.limit)
    }

    BufferedReader wrap(File file) {
        new BufferedReader(new FileReader(file))
    }

    @Override void act() {
        loop {
            react { message ->
                if (message instanceof BucketSpecification) {
                    process message
                }
            }
        }
    }

}
