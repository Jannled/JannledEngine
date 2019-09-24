#include "Scene.h"

Scene::Scene()
{

}

void Scene::add(Model &m)
{
	models.push_back(m);
}

void Scene::render()
{
	for(auto &model : models)
		model.render();
}

Scene::~Scene()
{

}
