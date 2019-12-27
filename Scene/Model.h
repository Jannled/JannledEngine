#ifndef MODEL_H
#define MODEL_H

#include "../lib/GL_4_6_core.h"

struct ModelData
{
	float *position;
	size_t c_position;

	float *normal;
	size_t c_normal;

	float *tangent;
	size_t c_tangent;

	float *texcoord_0;
	size_t c_texcoord_0;

	float *texcoord_1;
	size_t c_texcoord_1;

	float *color_0;

	float *joints_0;

	float *weights_0;

	unsigned int *indices;
	size_t c_indices;

	
	
	
	size_t c_color_0;
	size_t c_joints_0;
	size_t c_weights_0;
	
};

class Model
{
	public:
		Model(ModelData &modelData);
		Model();
		virtual ~Model();

		void render();

	private:
		ModelData modelData;

		//ID handles for their respective buffer object
		GLuint POSITION;
		GLuint NORMAL;
		GLuint TANGENT;
		GLuint TEXCOORD_0;
		GLuint TEXCOORD_1;
		GLuint COLOR_0;
		GLuint JOINTS_0;
		GLuint WEIGHTS_0;
		GLuint INDICES;
		GLuint VAO;
};

#endif //MODEL_H
