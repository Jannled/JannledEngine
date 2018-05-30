/*
 * Shader.h
 *
 *  Created on: 19.05.2018
 *      Author: Jannled
 */

#ifndef SHADER_SHADER_H_
#define SHADER_SHADER_H_

#include <iostream>
#include <GL/glew.h>
#include <GLFW/glfw3.h>

#include <fstream>
#include <sstream>
#include <iostream>

class Shader
{
public:
	GLuint shaderID;
	Shader(const char*, GLenum);
	virtual ~Shader();
};

#endif /* SHADER_SHADER_H_ */
