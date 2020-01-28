package interactions.commands

import interactions.{Command, CommandResult, Context}

class ExitCommand(context: Context) extends Command(context) {
  override def execute(): CommandResult = {
    executionContext.terminate(0)
    success()
  }
}




