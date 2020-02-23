package simple_prompt
import org.scalatest.matchers.should.Matchers
import org.scalatest.flatspec.AnyFlatSpec

class InputParserTest extends AnyFlatSpec with Matchers {

  it should "return new command run for ':new' command" in {
    val (cmd, args) = StringCommandExtractor.parse(":new")
    assert(cmd == "new")
    assert(args == "")
  }

  it should "return 'cmd' and 'arg1 arg2' for ':cmd arg1 arg2'" in {
    val (cmd, args) = StringCommandExtractor.parse(":cmd arg1 arg2")
    assert(cmd == "cmd")
    assert(args == "arg1 arg2")
  }

  it should "throw error if there is no colon before command" in {
    assertThrows[InvalidCommandFormat] {
      StringCommandExtractor.parse("cmd arg1 arg2")
    }
  }
}
