package simple_promt

import scala.io.StdIn.readLine

import core._
import simple_promt._

object TTESimplePromt extends App {
    val deck = new Deck()

    val cross_player = new Player("Cross", Mark.Cross)
    val nought_player = new Player("Nought", Mark.Nought)

    val instance = new Game(List(cross_player, nought_player), deck)

    val drawer = new DeckDrawer()

    val printer = new NaiveConsolePrinter()

    while (!instance.gameEnded) {
        printer.clrSrc()
        printer.print(drawer.makeDeckView(deck))
        printer.print(s"${instance.player.name}> ")

        val input = readLine().split(" ").map(_.toInt)

        printer.newLine()

        instance.makeTurn(input(0), input(1))
    }
    if (instance.winner.isDefined) {
        printer.printLn(s"Winner is ${instance.winner.get.name}")
    } else {
        printer.printLn("It's a tie!")
    }

}