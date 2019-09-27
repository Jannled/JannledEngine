#include "Model.h"

#include <iostream>

Model::Model(ModelData modelData)
{
	this->modelData = modelData;
	//TODO Implement Buffer loading!
}

void Model::render()
{
	glBindVertexArray(VAO);
    glDrawElements(GL_TRIANGLES, modelData.c_indices, GL_UNSIGNED_INT, 0);
}

Model::~Model()
{

}
