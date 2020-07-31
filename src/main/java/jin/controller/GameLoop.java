package jin.controller;

import jin.view.Renderer;
import jin.view.Window;

/**
 * <p>Implementing basic game-loop
 * @author nmd
 * @version 1.0
 * @since 2020-07-30
 */
public abstract class GameLoop {
	protected Window window = new Window("defualt GLFW window", 800, 600);
	protected Renderer renderer = new Renderer();

	/**
	 * <p>Will be called before game-loop starts
	 */
	protected abstract void create();

	/**
	 * <p>Will be called every iteration
	 */
	protected abstract void update();

	/**
	 * <p>Create window, renderer and start the game-loop
	 */
	public void start() {
		create();
		while (window.isRunning()) {
			renderer.clear();

			update();

			window.swap();
			window.pollEvents();
		}
	}
}
