/*
 * Shader.cpp
 *
 *  Created on: 19.05.2018
 *      Author: Jannled
 */

#include "Shader.h"

using namespace std;

Shader::Shader(const char *filePath, GLenum shaderType)
{
	string fileContent;
	ifstream file;
	file.exceptions (std::ifstream::failbit | std::ifstream::badbit);
	try
	{
		file.open(filePath);
		std::stringstream vShaderStream, fShaderStream;
		vShaderStream << file.rdbuf();
		file.close();
		fileContent = vShaderStream.str();
	}
	catch (std::ifstream::failure &e)
	{
		std::cout << "ERROR::SHADER::FILE_NOT_SUCCESFULLY_READ" << std::endl;
	}

	//Create Shader
	shaderID = glCreateShader(shaderType);
	const char *sourceCode = fileContent.c_str();
	glShaderSource(shaderID, 1, &sourceCode, NULL);
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

