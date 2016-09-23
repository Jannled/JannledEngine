#version 400

in vec3 vertexPositions;
in vec3 vertexColor;

uniform mat4 transform;

out vec3 fragColour;

void main() 
{
	fragColour = vertexColor;
	gl_Position = transform * vec4(vertexPositions, 1.0);
}
