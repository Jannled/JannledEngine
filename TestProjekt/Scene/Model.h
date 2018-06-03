/*
 * Model.h
 *
 *  Created on: 23.05.2018
 *      Author: Jannled
 */

#ifndef SHADER_MODEL_H_
#define SHADER_MODEL_H_

#include <GL/glew.h>
#include <glm/glm.hpp>

#include <assimp/Importer.hpp>
#include <assimp/scene.h>
#include <assimp/postprocess.h>

#include "../Shader/Shader.h"
#include "../Shader/ShaderProgram.h"

class Model
{
public:
	unsigned int vaoID;

	Model(float vertices[], unsigned int verticesLen, unsigned int indices[], unsigned int indicesLen);
	Model(aiMesh *mesh, const aiScene *scene);

	void render(ShaderProgram&);

	virtual ~Model();
};

#endif /* SHADER_MODEL_H_ */
