/*
 * ShaderProgram.h
 *
 *  Created on: 20.05.2018
 *      Author: Jannled
 */

#ifndef SHADER_SHADERPROGRAM_H_
#define SHADER_SHADERPROGRAM_H_

#include <iostream>

#include <glm/glm.hpp>
#include <glm/gtc/matrix_transform.hpp>
#include <glm/gtc/type_ptr.hpp>

#include "Shader.h"

class ShaderProgram {
public:
	GLint programID;
	glm::mat4 acticveProjection;

	ShaderProgram(Shader shaders[], unsigned int size);

	void useProgram();
	GLint getUniformLocation(const std::string name);
	void uploadMatrix(glm::mat4 matrix);

	virtual ~ShaderProgram();
};

#endif /* SHADER_SHADERPROGRAM_H_ */
