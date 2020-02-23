package interactions.commands

import interactions.{Command, CommandResult, Context}

object ExitCommand {
  val TERMINATOR = "TERMINATION"
}

class ExitCommand(context: Context) extends Command(context) {

  override def execute(): CommandResult = {
    context.printer("Exiting from program. See you later!")
    //context.terminate(0)
    success(ExitCommand.TERMINATOR)
  }
}




