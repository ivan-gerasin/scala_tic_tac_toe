import interactions.commands.SaveCommand

class SaveCommandTest extends UnitTest {

  it should "return InvalidSlotIdException if slot(filename) is empty" in {
    val context = mock[MockedContext]
    val cmd = new SaveCommand(context)
    cmd.setSlotId("").execute()
  }

  it should "should run context.writeToSlot if context.isSlotEmpty" in {
    val context = mock[MockedContext]
    val cmd = new SaveCommand(context)
    val game = MockedContext.gameFactory()
    (context.isSlotEmpty _).expects("filename").returning(true)
    cmd.setSlotId("filename").setGameInstance(game).execute()
  }

  it should "trying to save if overwrite accepted" in {
    val context = mock[MockedContext]
    val game = MockedContext.gameFactory()
    val cmd = new SaveCommand(context)
    (context.printer _).expects(*)
    (context.isSlotEmpty _).expects("filename").returning(false)
    (context.readInput _).expects().returning("yes")
    (context.writeToSlot _).expects("filename", game)

    val result = cmd.setSlotId("filename").setGameInstance(game).execute()
    assert(result.isSuccessful)
  }

  it should "print notification if askConfirm failed because of incorrect input" in {
    val context = mock[MockedContext]
    val game = MockedContext.gameFactory()
    val cmd = new SaveCommand(context)
    (context.printer _).expects(*)
    (context.isSlotEmpty _).expects("filename").returning(false)
    (context.readInput _).expects().returning("1234")
    (context.printer _).expects("Can't read user input. Abort saving process")

    val result = cmd.setSlotId("filename").setGameInstance(game).execute()
    assert(result.isFailed)
  }

  it should "print notification if overwrite declined and return it" in {
    val context = mock[MockedContext]
    val game = MockedContext.gameFactory()
    val cmd = new SaveCommand(context)
    (context.printer _).expects(*)
    (context.isSlotEmpty _).expects("filename").returning(false)
    (context.readInput _).expects().returning("no")
    (context.printer _).expects("Save cancelled")

    val result = cmd.setSlotId("filename").setGameInstance(game).execute()
    assert(result.isSuccessful)
  }

}
