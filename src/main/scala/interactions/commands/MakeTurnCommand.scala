package interactions.commands

import core.{CellOverrideException, GameEndedException}
import interactions.{Command, CommandResult, Context}

class MakeTurnCommand(context: Context) extends Command(context) {
  private var position: (Int, Int) = null
  private def row: Int = position._1
  private def col: Int = position._2

  def setArgs(args: String): this.type = {
    val ROW_ARG_POS = 0
    val COL_ARG_POS = 1
    val items: Array[String] = args.split(" ")
    if (items.length == 2) {
      this.position = (
        items(ROW_ARG_POS).toInt,
        items(COL_ARG_POS).toInt
      )
    } else {
      throw new IllegalArgumentException(s"MakeTurnCommand: invalid argument's number: 2 is expected, but ${items.length} found")
    }
    this
  }

  override def execute(): CommandResult = {
    try {
      context.makeTurn(row, col)
      success()
    } catch {
      case e: CellOverrideException => fail(e)
      case endGame: GameEndedException=> fail(endGame)
    }

  }
}
