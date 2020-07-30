package jin;

import static org.junit.Assert.*;

import jin.view.Window;
import org.junit.Test;

/**
 * <p>Dummy Window tester</p>
 * @author nmd
 * @version 1.0
 * @since 2020-07-30
 */
public class WindowTest {
	@Test
	public void testWindow() {
		Window classUnderTest = new Window("Test window", 800, 600);
		assertTrue("HelloLWJGL must have window after initialization", classUnderTest.getWindowId() != 0);
	}
}
