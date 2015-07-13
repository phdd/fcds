package de.tud.fcds

import de.tud.fcds.friendly.Algorithm
import de.tud.fcds.friendly.Calculator

class FriendlyNumbers {

    static void main(def args) {
        def cli = new CliBuilder(usage: 'friendlyNumbers')

        cli.with {
            i longOpt: 'input', args: 1, argName: 'path', required: true, 'file of space separated ranges'
        }

        def params = cli.parse(args)

        if (params) {
            def calc = new Calculator()
            def algo = new Algorithm(
                    inputPath: params.i,
                    calculator: calc,

                    rangeChangedCallback: {
                        println "Number ${it.from} to ${it.to}"
                    },

                    friendsFoundCallback: {
                        it.each { friends ->
                            println "${friends[0]} and ${friends[1]} are FRIENDLY"
                        }
                    })

            [calc, algo]*.start()
            [calc, algo]*.join()
        }
    }

}