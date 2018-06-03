/*
 * Scene.h
 *
 *  Created on: 01.06.2018
 *      Author: Jannled
 */

#ifndef SCENE_SCENE_H_
#define SCENE_SCENE_H_

#include <iostream>
#include <string>
#include <fstream>
#include <sstream>
#include <iostream>
#include <map>
#include <vector>

#include "Model.h"

using namespace std;

class Scene {
public:
	vector<Model> meshes;
	Scene();
	virtual ~Scene();
};

#endif /* SCENE_SCENE_H_ */
