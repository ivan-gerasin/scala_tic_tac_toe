package simple_prompt

abstract class CLIPrinter {
  def clrSrc(): Unit
  def print(str: String): Unit
  def printLn(str: String): Unit
}
