package app.basicCubeViewer;

import jin.controller.GameLoop;
import jin.view.Program;
import org.joml.Vector3f;

/**
 * <p>
 * Draw a cube, note that the vertex data is hardcoded
 *
 * @author nmd
 * @version 1.0
 * @since 2020-07-31
 */
public class BasicCubeViewer extends GameLoop {
	public Program program;

	@Override
	protected void setup() {
		window.setTitle("Jin - A cube!@#$%^&*()_+");
		program = new Program("BasicCubeViewer/cube.vs", "BasicCubeViewer/cube.fs");
	}

	@Override
	protected void update() {}

	static class Vertex {
		public Vector3f position;
		public Vector3f color;

		public Vertex(Vector3f position, Vector3f color) {
			this.position = position;
			this.color = color;
		}
	}

	private static Vertex[] generateCubeVertices(float size) {
		Vertex[] vertices = {new Vertex(new Vector3f(-size, -size, size), new Vector3f(-size, -size, size)),
							 new Vertex(new Vector3f(size, -size, size), new Vector3f(size, -size, size)),
							 new Vertex(new Vector3f(-size, size, size), new Vector3f(-size, size, size)),
							 new Vertex(new Vector3f(size, size, size), new Vector3f(size, size, size)),
							 new Vertex(new Vector3f(-size, -size, -size), new Vector3f(-size, -size, -size)),
							 new Vertex(new Vector3f(size, -size, -size), new Vector3f(size, -size, -size)),
							 new Vertex(new Vector3f(-size, size, -size), new Vector3f(-size, size, -size)),
							 new Vertex(new Vector3f(size, size, -size), new Vector3f(size, size, -size))};
		return vertices;
	}

	public static void main(String[] args) { new BasicCubeViewer().start(); }
}