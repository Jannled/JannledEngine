/*
 * Scene.cpp
 *
 *  Created on: 07.06.2018
 *      Author: Jannled
 */

#include "Scene.h"

Scene::Scene() {
	// TODO Auto-generated constructor stub

}

void Scene::render(ShaderProgram& shaderProgram)
{
	for(Model m : models)
	{
		m.render(shaderProgram);
	}
}

Scene::~Scene() {
	// TODO Auto-generated destructor stub
}

