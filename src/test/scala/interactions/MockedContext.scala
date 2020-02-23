import interactions.Context

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class MockedContext extends Context {

  private val printCapacitor = ArrayBuffer[String]()
  def lastPrinterCall: String = printCapacitor.last
  def printerCalls: List[String] = printCapacitor.toList

  override def printer(textToPrint: String): Unit = {
    printCapacitor.append(textToPrint)
  }

  var fakedInput: String = ""
  override def readInput(): String = fakedInput

  override def terminate(exitCode: Int): Unit = print(s"=== App termination call === Code: ${exitCode}")

  var isFileExists: Boolean = true
  override def isSlotEmpty(filename: String): Boolean = isFileExists

  val writeLog: mutable.Map[String, Serializable] = mutable.Map[String, Serializable]()
  override def writeToSlot(filename: String, data: Serializable): Unit = {
    writeLog += (filename -> data)
  }
}