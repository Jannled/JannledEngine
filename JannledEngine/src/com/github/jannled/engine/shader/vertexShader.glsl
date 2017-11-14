#version 400

in vec3 vertexPositions;
in vec3 vertexNormals;
in vec2 textureCoords;

uniform mat4 transform;

out vec3 fragColour;

void main() 
{
	fragColour = vec3(1.0, 1.0, 1.0);
	gl_Position = transform * vec4(vertexPositions, 1.0);
}