import interactions.commands.MakeTurnCommand
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class MakeTurnCommandTest extends AnyFlatSpec with Matchers  {
  val context = new MockedContext()

  it should "throws IllegalArgumentException in setArgs is number of args is not equal 2" in {
    val cmd = new MakeTurnCommand(context)
    assertThrows[IllegalArgumentException] {
      cmd.setArgs("")
    }
    assertThrows[IllegalArgumentException] {
      cmd.setArgs("1 2 3")
    }
    cmd.setArgs("1 2")
  }

  it should "call makeTurn with row/col provided in setArgs" in {
    val cmd = new MakeTurnCommand(context)
    cmd.setArgs("1 2")
    val result = cmd.execute()
    assert(result.isSuccessful)
    assert(context.makeTurnLastCall == (1,2))
  }


}
