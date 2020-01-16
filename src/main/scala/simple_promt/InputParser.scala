package simple_promt


class InputParser {
  def parse(str: String): Unit = {
    str match {
      case str.startsWith(":") => print(1)
      case _ => print(2)
    }
  }
}
