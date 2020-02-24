import interactions.commands.HelpCommand

class HelpCommandTest extends UnitTest {

  it should "call printer with some text" in {
    val context = mock[MockedContext]
    (context.printer _).expects("Some help text")

    val cmd = new HelpCommand(context)
    cmd.execute()
  }
}
