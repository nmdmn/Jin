package jin.view;

import static org.lwjgl.opengl.GL11.*;

/**
 * <p>OpenGL wrapper class
 * @author nmd
 * @version 1.0
 * @since 2020-07-30
 */
public class Renderer {
	/**
	 * <p>Set up clear color
	 */
	public Renderer() { glClearColor(1.f, .0f, .0f, 1.f); }

	/**
	 * <p>Clear the color and the depth buffers
	 */
	public void clear() { glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); }
}
