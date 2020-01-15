import org.scalatest.matchers.should.Matchers
import org.scalatest.flatspec.AnyFlatSpec

import core.Deck
import core.Mark

class DeckTest extends AnyFlatSpec with Matchers {

  "A Deck" should "have size 3 by default" in {
    val defaultDeck = new Deck()
    assert(defaultDeck.size == 3)
  }

  it should "have specific size if it provided in constructor" in {
    val customDeckSize = 5
    val customDeck = new Deck(customDeckSize)
    assert(customDeck.size == customDeckSize)
  }

  it should "be empty be default" in {
    val defaultDeck = new Deck()
    assert(defaultDeck.toVector.flatten.forall(_ == Mark.Empty))
  }

  it should "allow to put a some non Empty mark to any cell" in {
    val defaultDeck = new Deck()

    defaultDeck.put(1,1, Mark.Cross)
    defaultDeck.put(3,3, Mark.Nought)

    assert(defaultDeck.toVector(0)(0) == Mark.Cross)
    assert(defaultDeck.toVector(2)(2) == Mark.Nought)

    assert(defaultDeck.get(1,1) == Mark.Cross)
    assert(defaultDeck.get(3,3) == Mark.Nought)
  }

  it should "throw error if put called with value greater than size" in {
    val defaultDeck = new Deck()
    assertThrows[IllegalArgumentException] {
      defaultDeck.put(defaultDeck.size+3, 1, Mark.Cross)
    }
  }

  it should "throw error if put called with Mark.Empty" in {
    val defaultDeck = new Deck()
    assertThrows[IllegalArgumentException] {
      defaultDeck.put(1, 1, Mark.Empty)
    }
  }
}
