#ifndef MODEL_H
#define MODEL_H

#include "../lib/GL_4_6_core.h"

struct ModelData
{
	float *position;
	float *normal;
	float *tangent;
	float *texcoord_0;
	float *texcoord_1;
	float *color_0;
	float *joints_0;
	float *weights_0;
	float *indices;

	size_t c_position;
	size_t c_normal;
	size_t c_tangent;
	size_t c_texcoord_0;
	size_t c_texcoord_1;
	size_t c_color_0;
	size_t c_joints_0;
	size_t c_weights_0;
	size_t c_indices;
};

class Model
{
	public:
		Model(ModelData modelData);
		Model(cgltf_mesh glTF_mesh);
		virtual ~Model();
		void render();

	private:
		ModelData modelData;
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
