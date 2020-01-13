package core

object Mark extends Enumeration {
//  private case class Val(val name: String, val mark: Char) extends super.Val
  type Mark = Value
  val Cross, Nought, Empty = Value
}