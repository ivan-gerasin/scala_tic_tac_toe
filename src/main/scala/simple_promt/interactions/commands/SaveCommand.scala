package simple_promt.interactions.commands

import simple_promt.interactions.{Command, CommandResult, Context}

class SaveCommand(context: Option[Context] = None) extends Command(context) {
  private def isValidFilename(filename: String): Boolean = {
    filename.length > 0
  }

  private def isFileExists(filename: String): Boolean = {
    //some check will be implemented here
    true
  }

  override def execute(executionContext: Context): CommandResult = {
    val filename = executionContext.readInput()
    if (!isValidFilename(filename)) {
      return fail(new IllegalArgumentException("Invalid filename"))
    }

    if (isFileExists(filename)) {
      //ask for overwrite
      executionContext.printer(f"${filename} already exists. Overwrite?")
      val overwriteConfirm = new AskConfirmCommand().execute()


    } else {
      //write!
    }
  }
}
