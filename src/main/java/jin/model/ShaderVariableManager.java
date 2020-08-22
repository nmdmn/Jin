package jin.model;

import static org.lwjgl.opengl.GL20.*;

import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Map;
import org.lwjgl.system.MemoryStack;

public class ShaderVariableManager {
	class ShaderVariable {
		public final int id;
		public final int type;
		public final int size;
		public final String name;

		public ShaderVariable(int id, int type, int size, String name) {
			this.id = id;
			this.type = type;
			this.size = size;
			this.name = name;
		}

		@Override
		public String toString() {
			return "ShaderVariable [id=" + id + ", name=" + name + ", size=" + size + ", type=" + type + "]";
		}
	}

	private Map<String, ShaderVariable> attributes;
	private Map<String, ShaderVariable> uniforms;

	public ShaderVariableManager(int programId) {
		attributes = getActiveAttributes(programId);
		uniforms = getActiveUniforms(programId);

		attributes.forEach((key, value) -> { System.out.println(key + ": " + value); });
		uniforms.forEach((key, value) -> { System.out.println(key + ": " + value); });
	}

	private Map<String, ShaderVariable> getActiveAttributes(int programId) {
		Map<String, ShaderVariable> attributes = new HashMap<>();
		try (MemoryStack stack = MemoryStack.stackPush()) {
			IntBuffer attribCountBuf = stack.callocInt(1);
			glGetProgramiv(programId, GL_ACTIVE_ATTRIBUTES, attribCountBuf);
			int attribCount = attribCountBuf.get();
			for (int i = 0; i < attribCount; i++) {
				IntBuffer attribSizeBuf = stack.callocInt(1);
				IntBuffer attribTypeBuf = stack.callocInt(1);
				String attribName = glGetActiveAttrib(programId, i, attribSizeBuf, attribTypeBuf);
				attributes.put(attribName, new ShaderVariable(i, attribTypeBuf.get(), attribSizeBuf.get(), attribName));
			}
		}
		return attributes;
	}

	private Map<String, ShaderVariable> getActiveUniforms(int programId) {
		Map<String, ShaderVariable> uniforms = new HashMap<>();
		try (MemoryStack stack = MemoryStack.stackPush()) {
			IntBuffer uniformCountBuf = stack.callocInt(1);
			glGetProgramiv(programId, GL_ACTIVE_UNIFORMS, uniformCountBuf);
			int uniformCount = uniformCountBuf.get();
			for (int i = 0; i < uniformCount; i++) {
				IntBuffer uniformSizeBuf = stack.callocInt(1);
				IntBuffer uniformTypeBuf = stack.callocInt(1);
				String uniformName = glGetActiveUniform(programId, i, uniformSizeBuf, uniformTypeBuf);
				uniforms.put(uniformName,
							 new ShaderVariable(i, uniformTypeBuf.get(), uniformSizeBuf.get(), uniformName));
			}
		}
		return uniforms;
	}
}