package interactions.commands.exceptions

class CommandExceptions(msg: String) extends Exception(msg) {
  def this(message: String, cause: Throwable) {
    this(message)
    initCause(cause)
  }

  def this(cause: Throwable) {
    this(Option(cause).map(_.toString).orNull, cause)
  }

  def this() {
    this(null: String)
  }
}

class InvalidSlotIdException(msg: String) extends CommandExceptions(msg)
class SlotNotExistsException(msg: String) extends CommandExceptions(msg)
