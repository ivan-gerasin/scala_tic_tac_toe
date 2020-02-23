package interactions

abstract class Command(val executionContext: Context) {
  def execute(): CommandResult
  protected def success(payload: Any): SuccessCommandResult = CommandResult.success(payload)
  protected def fail(error: Throwable): FailedCommandResult = CommandResult.failed(error)
}