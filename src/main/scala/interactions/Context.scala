package interactions

abstract class Context {
  def printer(textToPrint: String)
  def readInput(): String
  def terminate(exitCode: Int)
  def isFileExists(filename: String): Boolean
  def writeToFile(filename: String, data: Serializable)
}