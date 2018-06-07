/*
 * ModelLoader.h
 *
 *  Created on: 31.05.2018
 *      Author: Jannled
 */

#ifndef SCENE_MODELLOADER_H_
#define SCENE_MODELLOADER_H_

#include <iostream>
#include <vector>

#include "Model.h"
#include "Scene.h"

class ModelLoader
{
public:
	Scene currentScene;

	ModelLoader();

	void loadModel(std::string path);

	virtual ~ModelLoader();

private:
	void processNode(aiNode *node, const aiScene *scene);
	Model processModel(aiMesh *mesh, const aiScene *scene);

};

#endif /* SCENE_MODELLOADER_H_ */
