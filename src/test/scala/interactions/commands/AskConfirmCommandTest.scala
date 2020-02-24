import java.util.InputMismatchException

import interactions.{FailedCommandResult, SuccessCommandResult}
import interactions.commands.{AskConfirmCommand, Confirm}

class AskConfirmCommandTest extends UnitTest{

  val context = new MockedContext()
  val confirmMsg = "Wish to proceed?"

  it should "print provided confirmation message" in {
    val context = mock[MockedContext]
    val cmd = new AskConfirmCommand(context, confirmMsg)
    (context.printer _).expects("Wish to proceed?")
    cmd.execute()
  }

  it should "return success result with CONFIRM value if user print 'yes'" in {
    context.fakedInput = Confirm.CONFIRM_STR_VAL
    val cmd = new AskConfirmCommand(context, confirmMsg)
    val result = cmd.execute()

    assert(result.isInstanceOf[SuccessCommandResult])
    assert(result.payload == Confirm.CONFIRM)
  }

  it should "return success result with CANCEL value if user print 'no'" in {
    context.fakedInput = Confirm.CANCEL_STR_VAL
    val cmd = new AskConfirmCommand(context, confirmMsg)
    val result = cmd.execute()

    assert(result.isInstanceOf[SuccessCommandResult])
    assert(result.payload == Confirm.CANCEL)
  }

  it should "return fail result with InputMismatchException if user print any except 'yes' or 'no'" in {
    context.fakedInput = "invalid string"
    val cmd = new AskConfirmCommand(context, confirmMsg)
    val result = cmd.execute()

    assert(result.isInstanceOf[FailedCommandResult])
    assert(result.payload.isInstanceOf[InputMismatchException])
  }

}