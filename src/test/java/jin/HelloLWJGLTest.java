package jin;

import static org.junit.Assert.*;

import org.junit.Test;

public class HelloLWJGLTest {
	@Test
	public void testHelloLWJGLHasWindow() {
		HelloLWJGL classUnderTest = new HelloLWJGL();
		classUnderTest.init();
		assertTrue("HelloLWJGL must have window after initialization", classUnderTest.getWindow() != 0);
	}
}
