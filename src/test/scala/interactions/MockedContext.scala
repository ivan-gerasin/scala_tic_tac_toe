import core.{Deck, Game, Mark, Player}
import interactions.Context

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object MockedContext {
  def gameFactory(): Game = {
    val players = List[Player](
      new Player("Player 1", Mark.Cross),
      new Player("Player 1", Mark.Nought)
    )
    val deck = new Deck()
    new Game(players, deck)
  }
}

class MockedContext extends Context {

  override def printer(textToPrint: String): Unit = {}

  var fakedInput: String = ""
  override def readInput(): String = fakedInput

  override def terminate(exitCode: Int): Unit = print(s"=== App termination call === Code: ${exitCode}")

  override def isSlotEmpty(filename: String): Boolean = false

  val writeLog: mutable.Map[String, Serializable] = mutable.Map[String, Serializable]()
  override def writeToSlot(filename: String, data: Serializable): Unit = {
    writeLog += (filename -> data)
  }

  var readSlotContainer = MockedContext.gameFactory()
  override def readSlot(slotId: SlotId): Game = {readSlotContainer}

  override def setCurrentGame(game: Game): Unit = ???

  override def makeTurn(row: Int, col: Int): Unit = {}
}