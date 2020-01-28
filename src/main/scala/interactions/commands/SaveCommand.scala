package interactions.commands

import interactions.{Command, CommandResult, Context}

class SaveCommand(context: Context) extends Command(context) {
  private var filename: String = ""
  private def setFilename(name: String): Unit = filename = name

  override def execute(): CommandResult = {
//    val filename = executionContext.readInput()
    if (!isValidFilename) {
      return fail(new IllegalArgumentException("Invalid filename"))
    }

    if (!this.isFileExists) {
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

  private def isValidFilename: Boolean = {
    filename.length > 0
  }

  private def isFileExists: Boolean = executionContext.isFileExists(filename)

  private def saveAttempt(): CommandResult = {
    try {
      save()
      success()
    } catch {
      case e: Exception => fail(e)
    }
  }

  private def save(): Unit = executionContext.writeToFile(filename, new Serializable {})

  private def askForOverwrite(): CommandResult = {
    val askForOverwriteMessage = f"${filename} already exists. Overwrite?"
    new AskConfirmCommand(executionContext, askForOverwriteMessage).execute()
  }
}
