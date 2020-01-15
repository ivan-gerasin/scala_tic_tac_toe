package core

final class GameEndedException extends Exception
final class CellOverrideException extends Exception

class Game(private val players: List[Player], val deck: Deck) {
  private var currentTurn: Int = 0
  private def nextTurn(): Unit = {
    this.currentTurn += 1
  }
  private var endGame = false
  private var winnerOfGame: Option[Player] = None

  private def checkWinner: Option[Player] = {
    val deck_view = deck.toVector
    val match_row = deck_view.exists(row => row.forall(_ == row.head && row.head != Mark.Empty))

    val transposed = deck_view.transpose
    val match_col = transposed.exists(row => row.forall(_ == row.head && row.head != Mark.Empty))

    val first_main_diagonal_element = deck_view.head.head
    var main_diagonal = first_main_diagonal_element != Mark.Empty
    if (main_diagonal) {
      for (i <- 0 until deck.size) {
        main_diagonal &= deck_view(i)(i) == first_main_diagonal_element
      }
    }

    val first_counter_diagonal_element = deck_view.head.last
    var counter_diagonal = first_counter_diagonal_element != Mark.Empty
    if (counter_diagonal) {
      for (i <- 0 until deck.size) {
        counter_diagonal &= deck_view(i)(deck.size-1-i) == first_counter_diagonal_element
      }
    }

    val has_winner = match_row || match_col || main_diagonal || counter_diagonal

    if (has_winner) Some(this.player) else None
  }

  def print = {
    val deck_view = deck.toVector
    for (i <- deck_view.map(_.mkString("|")) ) yield println(i)
  }

  private def noTurnAvailable: Boolean = currentTurn == scala.math.pow(deck.size, 2).toInt

  def gameTurn: Int = currentTurn + 1

  def player: Player = players(currentTurn % players.size)

  def gameEnded: Boolean = endGame

  def winner: Option[Player] = winnerOfGame

  def makeTurn(row: Int, col: Int): Unit = {
    if (endGame) {
      throw new GameEndedException
    }

    if (deck.get(row, col) != Mark.Empty) {
      throw new CellOverrideException
    }

    deck.put(row, col, player.mark)
    val winner = this.checkWinner
    if (winner.isDefined || noTurnAvailable) {
      winnerOfGame = winner
      endGame = true
    } else {
      nextTurn()
    }
  }
}
