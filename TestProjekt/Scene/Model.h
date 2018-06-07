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

#include <vector>

class Model
{
public:
	unsigned int vaoID;

	struct Vertex {
		glm::vec3 position;
		glm::vec3 normal;
		glm::vec2 texCoords;
	};

	struct Texture {
		unsigned int id;
		std::string type;
	};

	std::vector<Vertex> vertices;
	std::vector<unsigned int> indices;
	std::vector<Texture> textures;

	Model(aiMesh *mesh, const aiScene *scene);

	void render(ShaderProgram&);

	virtual ~Model();
};

#endif /* SHADER_MODEL_H_ */
