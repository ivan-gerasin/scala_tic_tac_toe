package core

import scala.collection.mutable.ArrayBuffer
import core.Mark._

@SerialVersionUID(1L)
class Deck(val size: Int = 3) extends Serializable{
  private val _deck: ArrayBuffer[ArrayBuffer[Mark]] = ArrayBuffer.fill(size, size)(Mark.Empty)

  private def fitSize(value: Int) = value < size

  private def update(row: Int, col: Int, value: Mark): Unit = {
    require(fitSize(row))
    require(fitSize(col))
    require(value != Mark.Empty)
    _deck(row)(col) = value
  }

  def put(row: Int, col: Int, mark: Mark): Unit = update(row-1, col-1, mark)

  def get(row: Int, col: Int): Mark = _deck(row-1)(col-1)

  def toVector: Vector[Vector[Mark]] = _deck.map(_.toVector).toVector
}
