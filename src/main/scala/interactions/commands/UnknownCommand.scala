package interactions.commands

import interactions.commands.exceptions.NoSuchCommand
import interactions.{Command, CommandResult, Context}

class UnknownCommand(context: Context) extends Command(context) {
  private var cmd: String = "{No command provided}"
  def setWrongCmdName(name: String): this.type = {
    cmd = name
    this
  }
  override def execute(): CommandResult = {
    val errorMessage = s"Failed to execute command ${this.cmd}: so such command"
    executionContext.printer(errorMessage)
    fail(new NoSuchCommand(errorMessage))
  }
}
