package jin.controller;

import jin.view.Program;
import jin.view.Renderer;
import jin.view.Window;

/**
 * <p>Implementing basic game-loop
 * @author nmd
 * @version 1.0
 * @since 2020-07-30
 */
public class GameLoop {

	/**
	 * <p>Create window, renderer and start the game-loop
	 * @param args not used
	 */
	public static void main(String[] args) {
		Window window = new Window("Jin - The badass java 3D engine", 800, 600);
		Renderer renderer = new Renderer();
		Program program = new Program("basic_shader.vs", "basic_shader.fs");
		while (window.isRunning()) {
			renderer.clear();

			// TODO game logic

			window.swap();
			window.pollEvents();
		}
	}
}
