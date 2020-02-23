package simple_prompt

class NaiveConsolePrinter extends CLIPrinter {
  def print(str: String): Unit = Predef.print(str)
  def printLn(str: String): Unit = Predef.println(str)
  def clrSrc(): Unit = Predef.print("\u001b[2J")

  def newLine(): Unit = this.print("")
}