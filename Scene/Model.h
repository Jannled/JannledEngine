#ifndef MODEL_H
#define MODEL_H

#include "../lib/GL_4_6_core.h"

class Model
{
	public:
		Model();
		virtual ~Model();

		void render();

	private:
		GLuint VBO;
		GLuint VAO;
		GLuint EBO;
};

#endif //MODEL_H
