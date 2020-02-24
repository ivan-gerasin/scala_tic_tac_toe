package simple_prompt

import java.io.{FileInputStream, FileOutputStream, ObjectInputStream, ObjectOutputStream}
import java.nio.file.{Files, Paths}

import core.Game
import interactions.Context

//This is mostly fake implementation just to see how it will go

class SimplePromptContext(printer: CLIPrinter) extends Context {
  private var currentGame: Game = null

  override def printer(textToPrint: String): Unit = {
    printer.printLn(textToPrint)
  }

  override def readInput(): String = {
    printer.printLn("") //Empty string to divide input line
    printer.print("Command> ") //Print prompt
    scala.io.StdIn.readLine()
  }

  override def terminate(exitCode: Int = 0): Unit = {
    System.exit(exitCode)
  }

  override def isSlotEmpty(slotId: SlotId): Boolean = {
    val savesDir = Paths.get("saves")
    if (!Files.exists(savesDir)) {
      Files.createDirectory(savesDir)
    }
    Files.exists(Paths.get(s"/tmp/${slotId}.tts"))
  }

  override def writeToSlot(slotId: SlotId, data: Serializable): Unit = {
    val path = s"/saves/${slotId}.tts"
    val file = new ObjectOutputStream(new FileOutputStream(path))
    file.writeObject(data)
    file.close()
  }

  override def readSlot(slotId: SlotId): Game = {
    val path = s"/saves/${slotId}.tts"
    val streamInput = new ObjectInputStream(new FileInputStream(path))
    val game = streamInput.readObject.asInstanceOf[Game]
    streamInput.close()
    game
  }

  override def setCurrentGame(game: Game): Unit = {
    currentGame = game
  }

  override def makeTurn(row: Int, col: Int): Unit = {
    currentGame.makeTurn(row, col)
  }
}
