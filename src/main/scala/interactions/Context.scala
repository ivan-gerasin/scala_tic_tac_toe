package interactions

import core.Game

abstract class Context {
  type SlotId = String

  def printer(textToPrint: String)
  def readInput(): String
  def terminate(exitCode: Int)
  def isSlotEmpty(slotId: SlotId): Boolean
  def writeToSlot(slotId: SlotId, data: Serializable)
  def readSlot(slotId: SlotId): Game // ???
}