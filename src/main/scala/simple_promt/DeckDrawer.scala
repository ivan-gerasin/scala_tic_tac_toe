package simple_promt

import core._
import core.Mark._

class MarkNotExistsAtDeckDrawerMap extends Exception

object DeckDrawer {
  type MarkMap = Map[Mark, Char]

  val classicMarks: MarkMap = Map(
    Empty  ->  '_',
    Cross  ->  'X',
    Nought ->  'O'
  )
}

import DeckDrawer._
class DeckDrawer(val markMap: MarkMap = DeckDrawer.classicMarks) {

  private val verticalSeparator = "|"

  private def markToStrChar(mark: Mark): String = {
    val markChar = markMap.get(mark)
    if (markChar.isEmpty) {
      throw new MarkNotExistsAtDeckDrawerMap
    }
    markChar.get.toString
  }

  def makeDeckView(deck: Deck): String = {
    val deck_view = deck.toVector
    deck_view.map(row => row.map(markToStrChar).mkString(verticalSeparator)).mkString("\n")
  }
}
