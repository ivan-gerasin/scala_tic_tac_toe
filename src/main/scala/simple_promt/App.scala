package simple_promt

import scala.io.StdIn.readLine

import core._

object TTESimplePromt extends App {
    val deck = new Deck()

    val cross_player = new Player("Cross", Mark.Cross)
    val nought_player = new Player("Nought", Mark.Nought)

    val instance = new Game(List(cross_player, nought_player), deck)

    while (!instance.gameEnded) {
        print("\u001b[2J")
        instance.print
        print(s"${instance.player.name}> ")
        val input = readLine().split(" ").map(_.toInt)
        println("")
        instance.makeTurn(input(0), input(1))
    }
    if (instance.winner.isDefined) {
        println(s"Winner is ${instance.winner.get.name}")
    } else {
        println("It's a tie!")
    }

}