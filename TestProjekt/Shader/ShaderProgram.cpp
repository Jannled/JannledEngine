/*
 * ShaderProgram.cpp
 *
 *  Created on: 20.05.2018
 *      Author: Jannled
 */

#include "ShaderProgram.h"

using namespace std;

ShaderProgram::ShaderProgram(Shader shaders[], int size)
{
	programID = glCreateProgram();
	for(unsigned int i=0; i<size; i++)
	{
		glAttachShader(programID, shaders[i].shaderID);
	}
	glLinkProgram(programID);

	// check for shader compile errors
	int success; glGetProgramiv(programID, GL_LINK_STATUS, &success);
	if (!success)
	{
		int logLen; glGetProgramiv(programID, GL_INFO_LOG_LENGTH, &logLen);
		char infoLog[logLen];
		glGetShaderInfoLog(programID, logLen, NULL, infoLog);
		cerr << "ShaderProgram(" << programID << ") linking failed with error message: " << infoLog << endl;
	}
	else
	{
		cout << "Successfully loaded ShaderProgram with id " << programID << "." << endl;
	}
}

void ShaderProgram::useProgram()
{
	glUseProgram(programID);
}

ShaderProgram::~ShaderProgram() {
	// TODO Auto-generated destructor stub
}

