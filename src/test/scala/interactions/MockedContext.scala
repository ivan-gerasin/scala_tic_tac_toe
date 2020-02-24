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

  var isSlotEmpty: Boolean = true
  override def isSlotEmpty(filename: String): Boolean = isSlotEmpty

  val writeLog: mutable.Map[String, Serializable] = mutable.Map[String, Serializable]()
  override def writeToSlot(filename: String, data: Serializable): Unit = {
    writeLog += (filename -> data)
  }

  var readSlotContainer = MockedContext.gameFactory()
  private val readSlotCapacitor = ArrayBuffer[String]()
  def readSlotCalls: List[String] = readSlotCapacitor.toList
  def readSlotLastCall: String = readSlotCapacitor.last
  override def readSlot(slotId: SlotId): Game = {
    readSlotCapacitor.append(slotId)
    readSlotContainer
  }

  override def setCurrentGame(game: Game): Unit = ???

  private val makeTurnCapacitor = ArrayBuffer[(Int, Int)]()
  def makeTurnLastCall: (Int, Int) = makeTurnCapacitor.last
  override def makeTurn(row: Int, col: Int): Unit = {
    makeTurnCapacitor.append((row, col))
  }
}