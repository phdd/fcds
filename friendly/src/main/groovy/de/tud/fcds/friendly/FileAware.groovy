package de.tud.fcds.friendly

trait FileAware {

    def map(List<List<String>> table) {
        [by: { Closure processor ->
            table.collect {
                processor.delegate = it
                processor.call()
            }
        }]
    }

    def read(String inputPath) {
        [using: { Closure processor ->
            new File(inputPath).collect {
                processor.delegate = it
                processor.call()
            }
        }]
    }

    def get(args) {
        [from: { List<List> table ->
            table.getAt(args.row).getAt(args.col)
        }]
    }

}
