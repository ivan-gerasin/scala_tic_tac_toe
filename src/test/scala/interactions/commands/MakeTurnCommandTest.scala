import interactions.commands.MakeTurnCommand

class MakeTurnCommandTest extends UnitTest  {

  it should "throws IllegalArgumentException in setArgs is number of args is not equal 2" in {
    val context = mock[MockedContext]
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
    val context = mock[MockedContext]
    val cmd = new MakeTurnCommand(context)
    cmd.setArgs("1 2")
    (context.makeTurn _).expects(1,2)
    val result = cmd.execute()
    assert(result.isSuccessful)
  }


}
