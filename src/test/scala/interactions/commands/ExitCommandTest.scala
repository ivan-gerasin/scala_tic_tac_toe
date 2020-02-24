import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import interactions.commands.{ExitCommand}

class ExitCommandTest extends AnyFlatSpec with Matchers  {
  val context = new MockedContext()

  it should "return ExitCommand.TERMINATOR on execution" in {
    val cmd = new ExitCommand(context)
    val result = cmd.execute()
    assert(result.isSuccessful)
    assert(result.payload == ExitCommand.TERMINATOR)
  }
}
