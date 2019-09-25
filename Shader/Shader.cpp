#include "Shader.h"

#include <iostream>

Shader::Shader(const char *code, GLenum type)
{
	this->type = type;

	switch (type) {
		case GL_VERTEX_SHADER: break;

		case GL_FRAGMENT_SHADER: break;

		default: std::cerr << "Invalid shadertype enum! " << type << std::endl; break;
	}

	shaderID = glCreateShader(type);
	glShaderSource(shaderID, 1, &code, NULL);
}

void Shader::compile()
{
	switch(type)
	{
		case GL_VERTEX_SHADER: std::cout << "Compiling Vertex-Shader (" << shaderID << "): "; break;
		case GL_FRAGMENT_SHADER: std::cout << "Compiling Fragment-Shader (" << shaderID << "): "; break;
		default: std::cout << "Compiling ?-Shader (" << shaderID << "): "; break;
	}

	glCompileShader(shaderID);
	GLint reval;
	glGetShaderiv(shaderID, GL_COMPILE_STATUS, &reval);
	if(!reval)
	{
		std::cout << "FAIL" << std::endl;
<<<<<<< HEAD
		glGetShaderiv(shaderID, GL_INFO_LOG_LENGTH, &reval);
		GLchar errorLog[reval];
		glGetShaderInfoLog(shaderID, reval, &reval, &errorLog[0]);
		std::cerr << errorLog << std::endl;
		glDeleteShader(shaderID);
		return;
=======
>>>>>>> 491d1f82ffc96331f157044555d4250e3f44fb82
	}
	else
		std::cout << "SUCCESS" << std::endl;
}

Shader::~Shader()
{

}
