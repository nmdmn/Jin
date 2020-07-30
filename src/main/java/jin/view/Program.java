package jin.view;

import static com.google.common.io.Files.getFileExtension;
import static org.lwjgl.opengl.GL20.*;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * <p>Program represents GLSL shader program
 * @author nmd
 * @version 1.0
 * @since 2020-07-30
 */
public class Program {
	private static final String VERTEX_SHADER_EXTENSION = "vs";
	private static final String FRAGMENT_SHADER_EXTENSION = "fs";
	private static final String SHADER_PATH_PREFIX = "shader/";

	private int programId;

	/**
	 * Load and compile shader files
	 * @param filenames shader file names relative from <i>[project-root]/shader/</i>
	 */
	public Program(String... filenames) {
		programId = glCreateProgram();
		for (String filename : filenames) {
			String shaderFileExtension = getFileExtension(filename);
			switch (shaderFileExtension) {
			case VERTEX_SHADER_EXTENSION:
				compileShader(loadShader(filename), GL_VERTEX_SHADER);
				break;
			case FRAGMENT_SHADER_EXTENSION:
				compileShader(loadShader(filename), GL_FRAGMENT_SHADER);
				break;
			default:
				throw new AssertionError("unsupported shader file extension!");
			}
		}
		glLinkProgram(programId);
		if (GL_FALSE == glGetProgrami(programId, GL_LINK_STATUS)) {
			String glError = glGetProgramInfoLog(programId);
			throw new AssertionError(String.format("linking shader program failed: %s", glError));

		}
	}

	private String loadShader(String filename) {
		String shaderSource;
		try (FileInputStream inputStream = new FileInputStream(SHADER_PATH_PREFIX + filename)) {
			shaderSource = new String(inputStream.readAllBytes());
		} catch (IOException e) {
			throw new AssertionError("shader file missing: " + filename);
		}
		return shaderSource;
	}

	private void compileShader(String shaderSource, int shaderType) {
		int shaderId = glCreateShader(shaderType);
		glShaderSource(shaderId, shaderSource);
		glCompileShader(shaderId);
		if (GL_FALSE == glGetShaderi(shaderId, GL_COMPILE_STATUS)) {
			String glError = glGetShaderInfoLog(shaderId);
			throw new AssertionError(String.format("shader compilation failed: %s", glError));
		}
		glAttachShader(programId, shaderId);
	}
}
