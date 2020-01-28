package interactions

object CommandResult {
  type Payload = Any
  def success(payload: Payload): SuccessCommandResult = new SuccessCommandResult(payload)
  def failed(payload: Throwable): FailedCommandResult = new FailedCommandResult(payload)
}

import CommandResult.Payload

abstract class CommandResult {
  val payload: Payload
  val isSuccessful: Boolean
  def isFailed: Boolean = !isSuccessful
}

class SuccessCommandResult private[interactions] (val payload: Payload = None) extends CommandResult {
  val isSuccessful = true
}

class FailedCommandResult private[interactions] (val payload: Throwable)  extends CommandResult {
  val isSuccessful = false
}
