import org.scalatest.matchers.should.Matchers
import org.scalatest.flatspec.AnyFlatSpec

import core._

class GameTest extends AnyFlatSpec with Matchers {

  def fixture = new {
    val deck = new Deck()
    val playerX = new Player("Player for X", Mark.Cross)
    val playerO = new Player("Player for O", Mark.Nought)
    val game = new Game(List(this.playerX, this.playerO), this.deck)
  }

  "A Game" should "start with 1 turn" in {
    assert(fixture.game.gameTurn == 1)
  }

  it should "point to first player at the start" in {
    val playerX = fixture.playerX
    val playerO = fixture.playerO
    val deck = fixture.deck
    val game = new Game(List(playerX, playerO), deck)
    assert(game.player == playerX)
  }

  it should "say that game is non ended" in {
    assert(!fixture.game.gameEnded)
  }

  it should "show flag gameEnded and return Player winner" in {
    val playerX = fixture.playerX
    val playerO = fixture.playerO
    val deck = fixture.deck
    val game = new Game(List(playerX, playerO), deck)
    game.makeTurn(1,1) //X
    game.makeTurn(2,1) //Y

    game.makeTurn(1,2) //X
    game.makeTurn(2,2) //Y

    game.makeTurn(1,3) //X

    assert(game.gameEnded)
    assert(game.player == playerX)
  }

  it should "end game when column completed" in {
    val game = fixture.game

    game.makeTurn(1,1) //X
    game.makeTurn(1,3) //Y

    game.makeTurn(2,1) //X
    game.makeTurn(1,2) //Y

    game.makeTurn(3,1) //X

    assert(game.gameEnded)
    assert(game.player.name == fixture.playerX.name)
  }

  it should "end game when diagonal completed" in {
    val game = fixture.game

    game.makeTurn(1,1) //X
    game.makeTurn(2,1) //Y

    game.makeTurn(2,2) //X
    game.makeTurn(2,3) //Y

    game.makeTurn(3,3) //X

    assert(game.gameEnded)
    assert(game.player.name == fixture.playerX.name)
  }

  it should "end game when counter diagonal completed" in {
    val game = fixture.game

    game.makeTurn(1,3) //X
    game.makeTurn(2,1) //Y

    game.makeTurn(2,2) //X
    game.makeTurn(2,3) //Y

    game.makeTurn(3,1) //X

    assert(game.gameEnded)
    assert(game.player.name == fixture.playerX.name)
  }

}