/*
 * ShaderProgramme.cpp
 *
 *  Created on: 12.05.2018
 *      Author: Jannled
 */

#include "ShaderProgram.h"

#include <iostream>

using namespace std;

unsigned int shaderProgram;

ShaderProgram::ShaderProgram(Shader *shader)
{
	shaderProgram = glCreateProgram();
	for(unsigned int i=0; i<(sizeof(Shader) / sizeof(shader)); i++)
	{
		glAttachShader(shaderProgram, shader[i].shaderID);
	}

	glLinkProgram(shaderProgram);

	int compileStaus;
	glGetProgramiv(shaderProgram, GL_LINK_STATUS, &compileStaus);
	if(compileStaus == GL_TRUE)
	{
		cout << "Shaderprogram " << shaderProgram << " compiled successful" << endl;
	}
	else
	{
		int logLen;
		glGetProgramiv(shaderProgram, GL_INFO_LOG_LENGTH, &logLen); //TODO Warum ist das 1. Argument kein gültiges Handle???
		char infoLog[logLen];
		glGetProgramInfoLog(shaderProgram, logLen, &logLen, infoLog);
		cerr << infoLog << endl;
	}
}

void ShaderProgram::useProgram()
{
	int compileStaus;
	glGetProgramiv(shaderProgram, GL_LINK_STATUS, &compileStaus);
	if(compileStaus == GL_TRUE)
	{
		cout << "Using shader program " << shaderProgram << "." << endl;
		glUseProgram(shaderProgram);
	}
	else
	{
		cerr << "Could not use shader program, it is not compiled successfully." << endl;
	}
}

ShaderProgram::~ShaderProgram()
{

}

