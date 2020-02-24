import core.Game
import interactions.commands.NewGameCommand

class NewGameCommandTest extends UnitTest {
  it should "return new Game instance" in {
    val context = new MockedContext()
    val cmd = new NewGameCommand(context)
    val result = cmd.execute()
    assert(result.isSuccessful)
    assert(result.payload.isInstanceOf[Game])
  }
}
