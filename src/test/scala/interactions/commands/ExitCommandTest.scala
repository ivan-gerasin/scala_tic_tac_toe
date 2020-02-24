import interactions.commands.{ExitCommand}

class ExitCommandTest extends UnitTest {
  val context = new MockedContext()

  it should "return ExitCommand.TERMINATOR on execution" in {
    val cmd = new ExitCommand(context)
    val result = cmd.execute()
    assert(result.isSuccessful)
    assert(result.payload == ExitCommand.TERMINATOR)
  }
}
