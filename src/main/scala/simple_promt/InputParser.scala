package simple_promt

import interactions.{Command, _}

//command factory actually
class InputParser() {
  def parse(str: String): Unit = {
    str match {
//      case Command("new") => ???
//      case Command("load") => ???
//      case Command("save") => ???
//      case Command("end") => ???
//      case Command("help") => new HelpCommand()
//      case Command("exit") => new ExitCommand()
      case _ => invalidCommand()
    }
  }

  def invalidCommand() = throw new RuntimeException("No such command")
}
