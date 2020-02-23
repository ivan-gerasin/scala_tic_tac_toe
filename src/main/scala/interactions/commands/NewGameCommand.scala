package interactions.commands

import interactions.{Command, CommandResult, Context}

class NewGameCommand(context: Context) extends Command(context)  {

  override def execute(): CommandResult = {
    CommandResult.success()
  }

}
