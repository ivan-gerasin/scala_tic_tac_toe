package interactions

object Command {
  def apply(cmd: String, rest: String = ""): String = f":${cmd} ${rest}"

  def unapply(arg: String): Option[(String, String)] =
    if (arg.startsWith(":")) {
      val raw_cmd = arg.substring(1)
      val items = raw_cmd.split(' ')
      val cmd = items.head
      val args = items.tail.mkString(" ")
      Some(cmd, args)
    } else None
}

abstract class Command(val executionContext: Context) {
  def execute(): CommandResult
  protected def success(payload: Any): SuccessCommandResult = CommandResult.success(payload)
  protected def fail(error: Throwable): FailedCommandResult = CommandResult.failed(error)
}