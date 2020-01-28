import java.util.InputMismatchException

import org.scalatest.matchers.should.Matchers
import org.scalatest.flatspec.AnyFlatSpec
import interactions.{FailedCommandResult, SuccessCommandResult}
import interactions.commands.{AskConfirmCommand, Confirm}

class AskConfirmCommandTest extends AnyFlatSpec with Matchers {

  val context = new MockedContext()
  val confirmMsg = "Wish to proceed?"

  it should "print provided confirmation message" in {
    val cmd = new AskConfirmCommand(context, confirmMsg)
    cmd.execute()

    assert(context.lastPrinterCall == confirmMsg)
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