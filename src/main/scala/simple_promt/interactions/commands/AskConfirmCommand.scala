package simple_promt.interactions.commands

import simple_promt.interactions.{Command, CommandResult, CommandVariables, Context}


class AskConfirmCommandVariables(val askConfirmText: String) extends CommandVariables


class AskConfirmCommand(context: Option[Context] = None) extends Command(context) {

  object Confirm extends Enumeration {
    val CONFIRM, CANCEL = Value
  }

  private def parseInput(str: String): Confirm.Value = {
    //some parsing
    Confirm.CONFIRM
  }

  private var confirmationText: String = "Yes/No?"
  // here is the problem - breaking interface
  override def setVars(cmdVars: AskConfirmCommandVariables): this.type = {
    confirmationText = cmdVars.askConfirmText
    return this
  }

  override def execute(executionContext: Context): CommandResult = {
    try {
      val input = parseInput(executionContext.readInput())
      CommandResult.success(input)
    } catch {
      case _: Throwable => fail(new IllegalArgumentException("Failed to read confirmation input"))
    }

  }
}
