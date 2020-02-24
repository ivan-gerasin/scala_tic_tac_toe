import interactions.commands.HelpCommand
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class HelpCommandTest extends AnyFlatSpec with Matchers  {
  val context = new MockedContext()

  it should "call printer with some text" in {
    val cmd = new HelpCommand(context)
    cmd.execute()
    assert(context.printerCalls.nonEmpty)
  }
}
