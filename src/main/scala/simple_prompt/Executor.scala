package simple_prompt

import interactions.{Command, CommandResult, Context}
import interactions.commands.{ExitCommand, HelpCommand, LoadCommand, MakeTurnCommand, NewGameCommand, SaveCommand, UnknownCommand}
import simple_prompt.UserInputString.CommandAndArgs

class Executor(context: Context) {
  private def createCommand(userCmdRequest: CommandAndArgs): Command = {
    val (cmd, arg) = userCmdRequest
    cmd match {
      case "new" => new NewGameCommand(context)
      case "save" => (new SaveCommand(context)).setSlotId(arg)
      case "load" => (new LoadCommand(context)).setSlotId(arg)
      case "help" => new HelpCommand(context)
      case "exit" => new ExitCommand(context)

      case "turn" => (new MakeTurnCommand(context)).setArgs(arg)

      case _ => (new UnknownCommand(context)).setWrongCmdName(cmd)
    }
  }

  def command(userCmdRequest: CommandAndArgs): CommandResult = {
    val cmd = createCommand(userCmdRequest)
    cmd.execute()
  }
}
