package app;

import jin.controller.GameLoop;
import jin.view.Program;
/**
 * <p>Basic application skeleton
 * @author nmd
 * @version 1.0
 * @since 2020-07-31
 */
public class BasicRedWindow extends GameLoop {
	public Program program;

	@Override
	protected void create() {
		program = new Program("BasicRedWindow/basic_shader.vs", "BasicRedWindow/basic_shader.fs");
	}

	@Override
	protected void update() {
		program.on();
		// TODO drawning
		program.off();
	}

	public static void main(String[] args) { new BasicRedWindow().start(); }
}