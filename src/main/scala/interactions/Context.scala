package interactions

import core.Game

abstract class Context {
  type SlotId = String

  // Common commands
  def printer(textToPrint: String)
  def readInput(): String
  def terminate(exitCode: Int)
  def isSlotEmpty(slotId: SlotId): Boolean
  def writeToSlot(slotId: SlotId, data: Serializable)
  def readSlot(slotId: SlotId): Game // ???

  // game interactions
  def setCurrentGame(game: Game): Unit
  def makeTurn(row: Int, col: Int): Unit
}