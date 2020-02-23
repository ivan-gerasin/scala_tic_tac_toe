package simple_prompt

import simple_prompt.UserInputString.CommandAndArgs

object StringCommandExtractor {
  def parse(str: String): CommandAndArgs = {
    str match {
      case UserInputString(cmd) => cmd
      case _ => invalidCommand()
    }
  }

  def invalidCommand() = throw new InvalidCommandFormat("No such command")
}

class InvalidCommandFormat(message: String) extends Exception(message) {
  def this(message: String, cause: Throwable) {
    this(message)
    initCause(cause)
  }

  def this(cause: Throwable) {
    this(Option(cause).map(_.toString).orNull, cause)
  }

  def this() {
    this(null: String)
  }
}