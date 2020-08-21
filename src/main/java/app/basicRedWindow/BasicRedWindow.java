package app.basicRedWindow;

import jin.controller.GameLoop;
import jin.view.Program;
import org.joml.Vector4f;

/**
 * <p>Basic application skeleton
 * @author nmd
 * @version 1.0
 * @since 2020-07-31
 */
public class BasicRedWindow extends GameLoop {
	public Program program = new Program("BasicRedWindow/basic_shader.vs", "BasicRedWindow/basic_shader.fs");

	@Override
	protected void setup() {
		window.setTitle("Jin - The badass 3D engine");
		window.setSize(1280, 720);
		renderer.setClearColor(new Vector4f(1.f, 0.f, 0.f, 1.f));
	}

	@Override
	protected void update() {
		program.on();
		// TODO drawning
		program.off();
	}

	public static void main(String[] args) { new BasicRedWindow().start(); }
}