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
		cerr << "Exception while reading file : " << filePath << "!" << std::endl;
	}
	catch (std::ios_base::failure &e)
	{
		cerr << "Exception while reading file : " << filePath << "!" << std::endl;
	}

	//Create Shader
	shaderID = glCreateShader(shaderType);
	const char *sourceCode = fileContent.c_str();
	glShaderSource(shaderID, 1, &sourceCode, NULL);
	glCompileShader(shaderID);
	// check for shader compile errors
	int success; glGetShaderiv(shaderID, GL_COMPILE_STATUS, &success);
	if (!success)
	{
		int logLen; glGetShaderiv(shaderID, GL_INFO_LOG_LENGTH, &logLen);
		char infoLog[logLen];
		glGetShaderInfoLog(shaderID, logLen, NULL, infoLog);
		cerr << "Shader(" << shaderID << ") compilation failed with error message: " << infoLog << endl;
	}
	else
	{
		cout << "Successfully loaded Shader with id " << shaderID << "." << endl;
	}
}

Shader::~Shader() {
	// TODO Auto-generated destructor stub
}

