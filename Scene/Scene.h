#ifndef SCENE_H
#define SCENE_H

#include <vector>

#include "Model.h"

class Scene
{
	public:
		Scene();
		virtual ~Scene();
		void add(Model &m);
		void render();
	private:
		std::vector<Model> models;
};

#endif //SCENE_H
