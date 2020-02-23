package simple_promt

import simple_promt.UserInputString.CommandAndArgs

object StringCommandExtractor {
  def parse(str: String): CommandAndArgs = {
    str match {
      case UserInputString(cmd) => cmd
      case _ => invalidCommand()
    }
  }

  def invalidCommand() = throw new RuntimeException("No such command")
}
