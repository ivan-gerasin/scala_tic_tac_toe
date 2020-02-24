import core.Game
import interactions.commands.LoadCommand
import interactions.commands.exceptions.{InvalidSlotIdException, SlotNotExistsException}

class LoadCommandTest extends UnitTest {

  it should "return InvalidSlotIdException if slot(filename) is empty string" in {
    val context = new MockedContext()
    val cmd = new LoadCommand(context)
    cmd.setSlotId("")
    val result = cmd.execute()
    assert(result.isFailed)
    assert(result.payload.isInstanceOf[InvalidSlotIdException])
  }

  it should "return SlotNotExistsException when slot(filename) is not exists" in {
    val context = mock[MockedContext]
    (context.isSlotEmpty _).expects("filename").returning(true)
    val cmd = new LoadCommand(context)
    cmd.setSlotId("filename")
    val result = cmd.execute()
    assert(result.isFailed)
    assert(result.payload.isInstanceOf[SlotNotExistsException])
  }

  it should "return Game instance in successful scenario" in {
    val context = mock[MockedContext]
    (context.isSlotEmpty _).expects("filename").returning(false)
    (context.readSlot _).expects("filename").returning(context.readSlotContainer)

    val cmd = new LoadCommand(context)
    cmd.setSlotId("filename")
    val result = cmd.execute()
    assert(result.isSuccessful)
    assert(result.payload.isInstanceOf[Game])
  }

}
