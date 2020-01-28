package interactions.commands

import java.util.InputMismatchException

import interactions.{Command, CommandResult, Context}

object Confirm extends Enumeration {
  val CONFIRM, CANCEL = Value

  val CONFIRM_STR_VAL = "yes"
  val CANCEL_STR_VAL = "no"
}

class AskConfirmCommand(context: Context, val confirmText: String) extends Command(context) {

  private def parseInput(str: String): Confirm.Value = {
    //it sticked to CLI interface, so other kind of interfaces
    // have to fake it
    val confirmPattern = ("^"+Confirm.CONFIRM_STR_VAL+"$").r
    val cancelPattern = ("^"+Confirm.CANCEL_STR_VAL+"$").r
    str.trim match {
      case confirmPattern() => Confirm.CONFIRM
      case cancelPattern() => Confirm.CANCEL
      case _ => throw new InputMismatchException("Failed to read confirmation input")
    }
  }

  override def execute(): CommandResult = {
    try {
      executionContext.printer(confirmText)
      val input = parseInput(executionContext.readInput())
      success(input)
    } catch {
      case e: Throwable => fail(e)
    }

  }
}
