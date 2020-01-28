package simple_promt.interactions

abstract class Context {
  def printer(textToPrint: String)
  def readInput(): String
  def terminate(exitCode: Int)
}