package simple_prompt

import scala.io.StdIn.readLine
import core._
import interactions.CommandResult
import interactions.commands.ExitCommand
import simple_prompt._

object TTESimplePrompt extends App {

    val drawer = new DeckDrawer()
    val printer = new NaiveConsolePrinter()
    val executionContext = new SimplePromptContext(printer)
    val executor = new Executor(executionContext)

    var continueFlag = true
    while(continueFlag) {
        val userInput = executionContext.readInput()
        try {
            val result = executor.command(StringCommandExtractor.parse(userInput))
            if (result.isFailed) {
                executionContext.printer(s"Failed to execute command ${userInput}, exiting")
                continueFlag = false
            }
            if (result.isSuccessful && result.payload.isInstanceOf[String] && result.payload == ExitCommand.TERMINATOR) {
                continueFlag = false
            }
        } catch {
            case err: InvalidCommandFormat => executionContext.printer("Invalid command format")
        }

    }

//    while (!instance.gameEnded) {
//        printer.clrSrc()
//        printer.print(drawer.makeDeckView(deck))
//        printer.print(s"${instance.player.name}> ")
//
//        val input = readLine().split(" ").map(_.toInt)
//
//        printer.newLine()
//
//        instance.makeTurn(input(0), input(1))
//    }
//    if (instance.winner.isDefined) {
//        printer.printLn(s"Winner is ${instance.winner.get.name}")
//    } else {
//        printer.printLn("It's a tie!")
//    }

}