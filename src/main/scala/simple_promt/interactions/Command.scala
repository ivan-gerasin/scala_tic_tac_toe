package simple_promt.interactions

object Command {
  def apply(cmd: String, rest: String = ""): String = f":${cmd} ${rest}"

  def unapply(arg: String): Option[(String, String)] =
    if (arg.startsWith(":")) {
      val raw_cmd = arg.substring(1)
      val items = raw_cmd.split(' ')
      val cmd = items.head
      val args = items.tail.mkString(" ")
      Some(cmd, args)
    } else None
}




abstract class Command(context: Option[Context] = None) {
  protected val storedContext: Context = if (context.isDefined) context.get else null
  abstract def execute(executionContext: Context = storedContext): CommandResult
  protected def success(payload: Any): SuccessCommandResult = CommandResult.success(payload)
  protected def fail(error: Throwable): FailedCommandResult = CommandResult.failed(error)
}

class ExitCommand(context: Option[Context] = None) extends Command(context) {
  override def execute(executionContext: Context = storedContext): CommandResult = {
    executionContext.terminate(0)
    success()
  }
}

class HelpCommand(context: Option[Context] = None) extends Command(context) {
  override def execute(executionContext: Context = storedContext): CommandResult = {
    executionContext.printer("Some help text")
    success()
  }
}

class SaveCommand(context: Option[Context] = None) extends Command(context) {
  private def isValidFilename(filename: String): Boolean = {
    filename.length > 0
  }

  private def isFileExists(filename: String): Boolean = {
    //some check will be implemented here
    true
  }

  override def execute(executionContext: Context): Unit = {
    val filename = executionContext.readInput()
    if (!isValidFilename(filename)) {
      return fail(new IllegalArgumentException("Invalid filename"))
    }

    if (isFileExists(filename)) {
      //ask for overwrite
    } else {
      //write!
    }

  }
}