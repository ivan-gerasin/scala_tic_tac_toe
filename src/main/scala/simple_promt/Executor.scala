package simple_promt

import interactions.Context
import interactions.commands.{ExitCommand, HelpCommand, LoadCommand, NewGameCommand, SaveCommand}
import simple_promt.UserInputString.CommandAndArgs

class Executor(context: Context) {
  def command(userCmdRequest: CommandAndArgs): Unit = {
    val (cmd, arg) = userCmdRequest
    cmd match {
      case "new" => new NewGameCommand(context)
      case "save" => (new SaveCommand(context)).setSlotId(arg)
      case "load" => (new LoadCommand(context)).setSlotId(arg)
      case "help" => new HelpCommand(context)
      case "exit" => new ExitCommand(context)
    }
  }
}
