package simple_promt.interactions

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

abstract class CommandVariables

abstract class Command(context: Option[Context] = None) {
  protected val storedContext: Context = if (context.isDefined) context.get else null
  abstract def execute(executionContext: Context = storedContext): CommandResult
  protected def success(payload: Any): SuccessCommandResult = CommandResult.success(payload)
  protected def fail(error: Throwable): FailedCommandResult = CommandResult.failed(error)

  def setVars(cmdVars: CommandVariables): this.type = {
    throw new NotImplementedError("Class does not support variables")
    return this
  }
}