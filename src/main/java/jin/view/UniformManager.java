package jin.view;

import gnu.trove.map.hash.THashMap;

import static org.lwjgl.opengl.GL20.*;

/**
 * <p>Utility class to handle uniforms.
 * Uniform are referenced by name and spelling errors can result
 * in hard to find errors. This utility class reads the shaders,
 * collects the uniforms and if the uniforms are retireved through
 * it, the names are handled in a safe way.
 * @author nagygr
 * @version 1.0
 * @since 2020-08-04
 */
public class UniformManager {
	/**
	 * <p>Holds data that describes a uniform.
	 */
	public static class UniformData {
		private final String type;
		private final String name;
		private int location;

		/**
		 * Creates a UniformData from its type, name and location.
		 */
		public UniformData(String type, String name, int location) {
			this.type = type;
			this.name = name;
			this.location = location;
		}

		/**
		 * Creates a UniformData from its type and name.
		 * The location is initialized to -1.
		 */
		public UniformData(String type, String name) {
			this(type, name, -1);
		}

		/**
		 * Gets the uniform type.
		 * @return the uniform type
		 */
		public String getType() {
			return type;
		}

		/**
		 * Gets the uniform name.
		 * @return the uniform name
		 */
		public String getName() {
			return name;
		}

		/**
		 * Gets the uniform location.
		 * @return the uniform location
		 */
		public int getLocation() {
			return location;
		}

		/**
		 * Sets the uniform location.
		 * @param locaiton the uniform location
		 */
		public void setLocation(int location) {
			this.location = location;
		}
	}

	private THashMap<String, UniformData> uniforms;
	private int programId;

	/**
	 * Creates a UniformManager with the corresponding program ID.
	 * @param programId the ID of the program that is parsed for uniforms
	 */
	public UniformManager(int programId) {
		uniforms = new THashMap<>();
		this.programId = programId;
	}

	/**
	 * Retrieves the uniform data for the given name.
	 * @param uniformName the name of the uniform
	 */
	public UniformData get(String uniformName) {
		return uniforms.get(uniformName);
	}

	/**
	 * Parses the shader text for uniforms.
	 * @param program the shader to parse
	 */
	public void getUniformsFrom(String program) {
		program.lines().forEach(s -> {
			String line = s.trim();
			if (line.startsWith("uniform")) {
				String[] elements = line.split("[\\s,;]");

				if (elements.length < 3)
					throw new AssertionError(String.format("Unable to parse shader line: %s", line));

				String type = elements[1];

				for (int i = 2; i < elements.length; ++i) {
					if (uniforms.get(elements[i]) != null)
						throw new AssertionError(String.format(
							"UniformManager already contains uniform: %s", elements[i]
						));
					uniforms.put(elements[i], new UniformData(type, elements[i]));
				}
			}
		});
	}

	/**
	 * Reads the uniform locations form the linked shader.
	 */
	public void readUniformLocations() {
		uniforms.forEachValue(o -> {
			UniformData data = (UniformData)o;
			data.setLocation(glGetUniformLocation(programId, data.getName()));

			if (data.getLocation() == -1)
				throw new AssertionError(String.format(
					"Uniform (%s) location could not be retrieved", data.getName()
				));

			return true;
		});
	}
}
