package jin.view;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.system.MemoryUtil.*;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

/**
 * <p>Class representing a GLWF window and add a basic interface to interact with
 * @author nmd
 * @version 1.0
 * @since 2020-07-30
 */
public class Window {
	private long windowId;

	/**
	 * <p>Get the GLFW window ID
	 * @return ID representing a GLFW window
	 */
	public long getWindowId() { return windowId; }

	/**
	 * <p>Initialize GLFW and create a window
	 * @param title Initial window title
	 * @param width Initial window width
	 * @param height Initial window Height
	 */
	public Window(String title, int width, int height) {
		initializeGlfw();
		setWindowHints();
		createWindow(title, width, height);
		finalizeWindow();
		setCallbacks();
	}

	/**
	 * <p>Swap window buffers
	 */
	public void swap() { glfwSwapBuffers(windowId); }

	/**
	 * <p>Check window should close
	 * @return true if still running, false if should close
	 */
	public boolean isRunning() { return !glfwWindowShouldClose(windowId); }

	/**
	 * <p>Handle events, here is where our callbacks would be called
	 */
	public void pollEvents() { glfwPollEvents(); }

	private void finalizeWindow() {
		glfwMakeContextCurrent(windowId);
		glfwShowWindow(windowId);
		GL.createCapabilities(); // XXX this will be a headache here later (threading issues)
	}

	private void setCallbacks() {
		glfwSetKeyCallback(windowId, this::keyChangedCallback);
		glfwSetFramebufferSizeCallback(windowId, this::frameBufferChangedCallback);
	}

	private void createWindow(String title, int width, int height) {
		windowId = glfwCreateWindow(width, height, title, NULL, NULL);
		if (NULL == windowId)
			throw new AssertionError("glfwCreateWindow() failed!");
	}

	private void setWindowHints() {
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
	}

	private void initializeGlfw() {
		GLFWErrorCallback.createPrint(System.err).set();
		if (!glfwInit())
			throw new AssertionError("glfwInit() failed!");
	}

	private void keyChangedCallback(long windowId, int key, int scancode, int action, int mods) {
		if ((key == GLFW_KEY_ESCAPE || key == GLFW_KEY_Q) && action == GLFW_RELEASE)
			glfwSetWindowShouldClose(windowId, true);
	}

	private void frameBufferChangedCallback(long windowId, int width, int height) { glViewport(0, 0, width, height); }
}
