/*
 * ShaderProgramme.h
 *
 *  Created on: 12.05.2018
 *      Author: Jannled
 */

#ifndef SHADERPROGRAM_H_
#define SHADERPROGRAM_H_

#include <GL/glew.h>
#include "Shader.h"

class ShaderProgram {
public:
	ShaderProgram(Shader *shader);
	void useProgram();
	virtual ~ShaderProgram();
};

#endif /* SHADERPROGRAM_H_ */
