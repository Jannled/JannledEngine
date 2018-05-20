/*
 * ShaderProgram.cpp
 *
 *  Created on: 20.05.2018
 *      Author: Jannled
 */

#include "ShaderProgram.h"

GLint programID;

ShaderProgram::ShaderProgram(Shader shaders[], int size)
{
	programID = glCreateProgram();
	for(unsigned int i=0; i<size; i++)
	{
		glAttachShader(programID, shaders[i].shaderID);
	}
	glLinkProgram(programID);
}

void ShaderProgram::useProgram()
{
	glUseProgram(programID);
}

ShaderProgram::~ShaderProgram() {
	// TODO Auto-generated destructor stub
}

