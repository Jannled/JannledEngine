/*
 * Scene.h
 *
 *  Created on: 07.06.2018
 *      Author: Jannled
 */

#ifndef SCENE_SCENE_H_
#define SCENE_SCENE_H_

#include <vector>

#include "../Shader/ShaderProgram.h"
#include "Model.h"

class Scene {
public:
	std::vector<Model> models;

	Scene();

	void render(ShaderProgram& shaderProgram);

	virtual ~Scene();
};

#endif /* SCENE_SCENE_H_ */
