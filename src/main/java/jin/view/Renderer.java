package jin.view;

import static org.lwjgl.opengl.GL11.*;

import org.joml.Vector4f;
import org.lwjgl.opengl.GL;

/**
 * <p>OpenGL wrapper class
 * @author nmd
 * @version 1.0
 * @since 2020-07-30
 */
public class Renderer {
	/**
	 * <p>Create Renderer
	 */
	public Renderer() {
		GL.createCapabilities();
		setClearColor(new Vector4f(0.1f, 0.2f, 0.3f, 1.f));
	}

	/**
	 * <p>Set clear color
	 */
	public void setClearColor(Vector4f clearColor) {
		glClearColor(clearColor.x, clearColor.y, clearColor.z, clearColor.w);
	}

	/**
	 * <p>Clear the color and the depth buffers
	 */
	public void clear() { glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); }
}
