package core

import scala.collection.mutable.ArrayBuffer
import core.Mark._

class Deck(val deckSize: Int = 3) {
  private val _deck: ArrayBuffer[ArrayBuffer[Mark]] = ArrayBuffer.fill(deckSize, deckSize)(Mark.Empty)

  private def fitSize(value: Int) = value < deckSize

  private def update(row: Int, col: Int, value: Mark): Unit = {
    require(fitSize(row))
    require(fitSize(col))
    _deck(row)(col) = value
  }

  def put(row: Int, col: Int, mark: Mark): Unit = update(row-1, col-1, mark)

  def get(row: Int, col: Int): Mark = _deck(row-1)(col-1)

  def deckAsVector: Vector[Vector[Mark]] = _deck.map(_.toVector).toVector
}
