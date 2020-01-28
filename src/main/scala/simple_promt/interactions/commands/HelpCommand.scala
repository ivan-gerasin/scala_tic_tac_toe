package simple_promt.interactions.commands

import simple_promt.interactions.{Command, CommandResult, Context}

class HelpCommand(context: Option[Context] = None) extends Command(context) {
  override def execute(executionContext: Context = storedContext): CommandResult = {
    executionContext.printer("Some help text")
    success()
  }
}
