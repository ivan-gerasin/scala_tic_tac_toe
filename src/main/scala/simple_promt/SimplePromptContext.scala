package simple_promt

import core.Game
import interactions.Context

//This is mostly fake implementation just to see how it will go

class SimplePromptContext(printer: CLIPrinter) extends Context {
  private var currentGame: Game = null

  override def printer(textToPrint: String): Unit = {
    printer.printLn(textToPrint)
  }

  override def readInput(): String = {
    printer.printLn("") //Empty string to divide input line
    printer.print("Command> ") //Print prompt
    scala.io.StdIn.readLine()
  }

  override def terminate(exitCode: Int = 0): Unit = {
    System.exit(exitCode)
  }

  override def isSlotEmpty(slotId: SlotId): Boolean = ???

  override def writeToSlot(slotId: SlotId, data: Serializable): Unit = ???

  override def readSlot(slotId: SlotId): Game = ???

  override def setCurrentGame(game: Game): Unit = {
    currentGame = game
  }

  override def makeTurn(row: Int, col: Int): Unit = {
    currentGame.makeTurn(row, col)
  }
}
