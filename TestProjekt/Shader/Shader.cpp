/*
 * Shader.cpp
 *
 *  Created on: 19.05.2018
 *      Author: Jannled
 */

#include "Shader.h"

using namespace std;

Shader::Shader(const char *fileContent, GLenum shaderType)
{
	//Create Shader
	shaderID = glCreateShader(shaderType);
	glShaderSource(shaderID, 1, &fileContent, NULL);
	glCompileShader(shaderID);
	// check for shader compile errors
	int success;
	char infoLog[512];
	glGetShaderiv(shaderID, GL_COMPILE_STATUS, &success);
	if (!success)
	{
		glGetShaderInfoLog(shaderID, 512, NULL, infoLog);
		std::cout << "ERROR::SHADER::VERTEX::COMPILATION_FAILED\n" << infoLog << std::endl;
	}
	else
	{
		cout << "Successfully loader Shader with id " << shaderID << "." << endl;
	}
}

Shader::~Shader() {
	// TODO Auto-generated destructor stub
}

