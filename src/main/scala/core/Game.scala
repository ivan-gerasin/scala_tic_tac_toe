package core

class Game(val players: List[Player], val deck: Deck) {
  private var currentTurn: Int = 0
  private def nextTurn: Int = {
    this.currentTurn += 1
    this.currentTurn
  }
  def turn: Int = currentTurn + 1
  def player: Player = players(currentTurn % players.size)
  def makeTurn(row: Int, col: Int): Unit = {
    deck.put(row, col, player.mark)
  }
}
