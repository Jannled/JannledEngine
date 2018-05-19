/*
 * Shader.cpp
 *
 *  Created on: 12.05.2018
 *      Author: Jannled
 */

#include "Shader.h"

using namespace std;

int compileStatus;
GLenum shaderType;
std::string sourceCode;

Shader::Shader(char *filePath, GLenum shaderType)
{
	Shader::shaderType = shaderType;
	std::string sourceCode = "";

	//Read file
	std::ifstream file;
	file.exceptions(std::ifstream::failbit | std::ifstream::badbit);
	try
	{
		file.open(filePath);
		std::stringstream fileStream;
		fileStream << file.rdbuf();
		file.close();
		sourceCode = fileStream.str();
	}
	catch(std::ifstream::failure &e)
	{
		std::cout << "Error while reading file from path: " << filePath << std::endl;
	}

	//Create Shader
	shaderID = glCreateShader(GL_VERTEX_SHADER);
	const char *cFileContent = sourceCode.c_str();
	glShaderSource(shaderID, 1, &cFileContent, NULL);
	glCompileShader(shaderID);

	cout << "Loading shader with ID " << shaderID << "." << endl;

	//Check for errors
	int logLen;
	int charsWritten;
	glGetShaderiv(shaderID, GL_COMPILE_STATUS, &compileStatus);
	glGetShaderiv(shaderID, GL_INFO_LOG_LENGTH, &logLen);

	if(compileStatus == GL_TRUE)
	{
		cout << "Shader ID " << shaderID << " compiled successful." << endl;
	}
	else
	{
		char infoLog[logLen];
		glGetShaderInfoLog(shaderID, logLen, &charsWritten, infoLog);
		cerr << "Shader(" << shaderID << ") compilation failed with error message: " << infoLog << endl;
	}
}

Shader::~Shader()
{

}

