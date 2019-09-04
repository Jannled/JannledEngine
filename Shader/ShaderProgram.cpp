#include "ShaderProgram.h"

#include <iostream>

ShaderProgram::ShaderProgram(Shader *vertexShader, Shader *fragmentShader)
{
	shaderID = glCreateProgram();
	glAttachShader(vertexShader->shaderID);
	glAttachShader(fragmentShader->shaderID);
}

void ShaderProgram::link()
{
	glLinkProgram(shaderID);
	GLint reval;
	glGetProgramiv(shaderID, GL_LINK_STATUS, &reval);
	if(!reval)
	{
		std::cout << "Linking failed" << std::endl;
	}
	else
		std::cout << "Linked sucessfully" << std::endl;
}
