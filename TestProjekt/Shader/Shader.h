/*
 * Shader.h
 *
 *  Created on: 12.05.2018
 *      Author: Jannled
 */

#ifndef SHADER_H_
#define SHADER_H_

#include <iostream>
#include <fstream>
#include <sstream>

#include <GL/glew.h>

class Shader {
public:
	unsigned int shaderID;
	GLenum shaderType;
	std::string sourceCode;

	Shader(char*, GLenum);
	virtual ~Shader();
};

#endif /* SHADER_H_ */
