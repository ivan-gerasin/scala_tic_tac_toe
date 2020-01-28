package interactions.commands

import interactions.{Command, CommandResult, Context}

class HelpCommand(context: Context) extends Command(context) {
  override def execute(): CommandResult = {
    executionContext.printer("Some help text")
    success()
  }
}
