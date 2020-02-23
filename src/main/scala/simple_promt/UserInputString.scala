package simple_promt

object UserInputString {

  type CommandAndArgs = (String, String)

  def apply(cmd: String, rest: String = ""): String = f":${cmd} ${rest}"

  def unapply(arg: String): Option[CommandAndArgs] =
    if (arg.startsWith(":")) {
      val raw_cmd = arg.substring(1)
      val items = raw_cmd.split(' ')
      val cmd = items.head
      val args = items.tail.mkString(" ")
      Some(cmd, args)
    } else None
}