package de.tud.fcds

import de.tud.fcds.friendly.Algorithm
import de.tud.fcds.friendly.Calculator

class FriendlyNumbers {

    static void main(def args) {
        def cli = new CliBuilder(usage: 'friendlyNumbers')

        cli.with {
            s longOpt: 'start', args: 1, argName: 'number', required: true, 'first number of range'
            e longOpt: 'end', args: 1, argName: 'number', required: true, 'last number of range'
        }

        def params = cli.parse(args)

        if (params) {
            int poolSize = Runtime.runtime.availableProcessors()
            def calc = new Calculator(size: poolSize)
            def algo = new Algorithm(
                    start: params.s.toLong(),
                    end: params.e.toLong(),
                    size: poolSize,
                    calculator: calc,
                    callback: {
                        it.each { friends ->
                            println "${friends.join ', '}"
                        }
                    })

            [calc, algo]*.start()
            [calc, algo]*.join()
        }
    }

}