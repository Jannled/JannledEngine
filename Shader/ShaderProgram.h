#ifndef SHADERPROGRAM_H
#define SHADERPROGRAM_H

#include "../lib/GL_4_6_core.h"

class ShaderProgram
{
public:
	ShaderProgram(Shader *vertexShader, Shader *fragmentShader);
	virtual ~ShaderProgram();
	void link();

protected:
	GLenum type;
	int shaderID;
};

#endif //SHADERPROGRAM_H
