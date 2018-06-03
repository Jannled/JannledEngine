/*
 * ShaderProgram.h
 *
 *  Created on: 20.05.2018
 *      Author: Jannled
 */

#ifndef SHADER_SHADERPROGRAM_H_
#define SHADER_SHADERPROGRAM_H_

#include <iostream>
#include "Shader.h"

class ShaderProgram {
public:
	GLint programID;
	ShaderProgram(Shader shaders[], int size);
	void useProgram();
	virtual ~ShaderProgram();
};

#endif /* SHADER_SHADERPROGRAM_H_ */
