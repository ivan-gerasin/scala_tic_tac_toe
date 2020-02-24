package interactions.commands

import interactions.commands.exceptions.{InvalidSlotIdException, SlotNotExistsException}
import interactions.{Command, CommandResult, Context}

class LoadCommand(context: Context) extends Command(context) {

  private var slotId: String = ""
  def setSlotId(slot: String): this.type  = {
    slotId = slot
    this
  }

  override def execute(): CommandResult = {

    if (!isValidSlotId) {
      return fail(new InvalidSlotIdException(s"Invalid slot id while trying to load: ${this.slotId}"))
    }

    if (isSlotEmpty) {
      return fail(new SlotNotExistsException(s"Slot with name ${this.slotId} is not exists or not found"))
    }

    try {
      CommandResult.success(executionContext.readSlot(this.slotId))
    } catch {
      case e: Exception => CommandResult.failed(e)
    }


  }

  private def isValidSlotId: Boolean = slotId.length > 0
  private def isSlotEmpty: Boolean = executionContext.isSlotEmpty(slotId)
}
