package jin;

import org.junit.Test;
import static org.junit.Assert.*;

public
class HelloLWJGLTest {
  @Test public void testHelloLWJGLHasWindow() {
    HelloLWJGL classUnderTest = new HelloLWJGL();
    classUnderTest.init();
    assertTrue("HelloLWJGL must have window after initialization",
               classUnderTest.getWindow() != 0);
  }
}
