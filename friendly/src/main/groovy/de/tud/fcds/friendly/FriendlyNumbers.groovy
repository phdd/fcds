package de.tud.fcds.friendly

class FriendlyNumbers {

    static void main(def args) {
        def cli = new CliBuilder(usage: 'friendlyNumbers')

        cli.with {

        }

        def params = cli.parse(args)

        if (params)
            println 'Yay...'
    }

}