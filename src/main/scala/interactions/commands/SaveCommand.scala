package interactions.commands

import core.Game
import interactions.commands.exceptions.InvalidSlotIdException
import interactions.{Command, CommandResult, Context}

class SaveCommand(context: Context) extends Command(context) {
  private var slotId: String = ""
  def setSlotId(name: String): this.type = {
    slotId = name
    this
  }

  private var gameInstance: Game = null
  def setGameInstance(game: Game): this.type = {
    gameInstance = game
    this
  }

  override def execute(): CommandResult = {

    if (gameInstance == null) {
      fail(new NullPointerException("SaveCommand: no game instance declared"))
    }

    if (!isValidSlotId) {
      return fail(new InvalidSlotIdException(s"Invalid slot id while trying to save: ${this.slotId}"))
    }

    if (this.isSlotEmpty) {
      return saveAttempt()
    }

    val overwriteConfirm = askForOverwrite()
    if (overwriteConfirm.isSuccessful && overwriteConfirm.payload == Confirm.CONFIRM) {
      saveAttempt()
    } else if (overwriteConfirm.isFailed){
      executionContext.printer("Can't read user input. Abort saving process")
      overwriteConfirm
    } else {
      executionContext.printer("Save cancelled")
      success()
    }
  }

  private def isValidSlotId: Boolean = {
    slotId.length > 0 // basically, slotId is a filename, probably will refactor in future
  }

  private def isSlotEmpty: Boolean = executionContext.isSlotEmpty(slotId)

  private def saveAttempt(): CommandResult = {
    try {
      save()
      success()
    } catch {
      case e: Exception => fail(e)
    }
  }

  private def save(): Unit = executionContext.writeToSlot(slotId, gameInstance)

  private def askForOverwrite(): CommandResult = {
    val askForOverwriteMessage = f"${slotId} already exists. Overwrite?"
    new AskConfirmCommand(executionContext, askForOverwriteMessage).execute()
  }
}
