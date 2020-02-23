package interactions.commands

import core.{Deck, Game, Mark, Player}
import interactions.{Command, CommandResult, Context}

class NewGameCommand(context: Context) extends Command(context)  {

  override def execute(): CommandResult = {
    val deck = new Deck()

    val cross_player = new Player("Cross", Mark.Cross)
    val nought_player = new Player("Nought", Mark.Nought)
    val instance = new Game(List(cross_player, nought_player), deck)

    CommandResult.success(instance)
  }

}
