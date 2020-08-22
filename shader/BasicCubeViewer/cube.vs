#version 330

layout (location = 0) in vec3 vs_position;
layout (location = 1) in vec3 vs_color;

out vec3 fs_color;

uniform mat4 u_world;
uniform mat4 u_view;
uniform mat4 u_proj;

void main()
{
	gl_Position = u_proj * u_view * u_world * vec4(vs_position, 1);
    fs_color = vs_color;
}
