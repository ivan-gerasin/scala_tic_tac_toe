package simple_promt.interactions

abstract class Context {
  def printer(textToPrint: String)
  def terminate(exitCode: Int)
}