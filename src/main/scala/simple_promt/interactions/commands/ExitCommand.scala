package simple_promt.interactions.commands

import simple_promt.interactions.{Command, CommandResult, Context}

class ExitCommand(context: Option[Context] = None) extends Command(context) {
  override def execute(executionContext: Context = storedContext): CommandResult = {
    executionContext.terminate(0)
    success()
  }
}




