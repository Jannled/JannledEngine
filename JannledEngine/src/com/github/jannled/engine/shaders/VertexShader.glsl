#version 400

in vec3 vertexPositions;
in vec3 vertexColor;

out vec3 fragColour;

void main() 
{
	fragColour = vec3(vertexColor);
	gl_Position = vec4(vertexPositions, 1.0);
}
