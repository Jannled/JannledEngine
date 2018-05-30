/*
 * Model.cpp
 *
 *  Created on: 23.05.2018
 *      Author: Jannled
 */

#include "Model.h"

struct Vertex {
	glm::vec3 position;
	glm::vec3 normal;
	glm::vec3 texCoords;
};

struct Texture {
	unsigned int id;
	std::string type;
};

Model::Model(float vertices[], unsigned int verticesLen, unsigned int indices[], unsigned int indicesLen)
{
	unsigned int VBO, EBO;
	glGenVertexArrays(1, &vaoID);
	glGenBuffers(1, &VBO);
	glGenBuffers(1, &EBO);
	// bind the Vertex Array Object first, then bind and set vertex buffer(s), and then configure vertex attributes(s).
	glBindVertexArray(vaoID);

	glBindBuffer(GL_ARRAY_BUFFER, VBO);
	glBufferData(GL_ARRAY_BUFFER, verticesLen * sizeof(float), vertices, GL_STATIC_DRAW);

	glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, EBO);
	glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesLen * sizeof(unsigned int), indices, GL_STATIC_DRAW);

	glVertexAttribPointer(0, 3, GL_FLOAT, GL_FALSE, 3 * sizeof(float), (void*)0);
	glEnableVertexAttribArray(0);

	glBindBuffer(GL_ARRAY_BUFFER, 0);

	glBindVertexArray(0);
}

Model::~Model() {
	// TODO Auto-generated destructor stub
}
