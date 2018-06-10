/*
 * ShaderProgram.cpp
 *
 *  Created on: 20.05.2018
 *      Author: Jannled
 */

#include "ShaderProgram.h"

using namespace std;

ShaderProgram::ShaderProgram(Shader shaders[], unsigned int size)
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

GLint ShaderProgram::getUniformLocation(const std::string name)
{
	GLint aloc = glGetUniformLocation(programID, name.c_str());
	if(aloc < 0) std::cerr << "Could not find a shader attrib with name " << name << "!";
	return aloc;
}

void ShaderProgram::uploadMatrix(glm::mat4 matrix)
{
	GLint aloc = getUniformLocation("mvpm");
	glUniformMatrix4fv(aloc, 1, GL_FALSE, glm::value_ptr(matrix));
}

ShaderProgram::~ShaderProgram() {
	// TODO Auto-generated destructor stub
}

