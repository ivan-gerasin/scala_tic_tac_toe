package simple_promt

import simple_promt.interactions._

//command factory actually
class InputParser() {
  def parse(str: String): Unit = {
    str match {
      case Command("new") => ???
      case Command("load") => ???
      case Command("save") => ???
      case Command("end") => ???
      case Command("help") => new HelpCommand()
      case Command("exit") => new ExitCommand()
      case _ => invalidCommand()
    }
  }

  def invalidCommand() = throw new RuntimeException("No such command")
}
