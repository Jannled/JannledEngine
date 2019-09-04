#ifndef SHADER_H
#define SHADER_H

#include "../lib/GL_4_6_core.h"

class Shader
{
public:
	Shader(const char *code, GLenum type);
	virtual ~Shader();
	void compile();

protected:
	GLenum type;
	int shaderID;
};

#endif //SHADER_H
