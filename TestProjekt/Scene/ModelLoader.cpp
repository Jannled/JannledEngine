/*
 * ModelLoader.cpp
 *
 *  Created on: 31.05.2018
 *      Author: Jannled
 */

//Why the hell does it only work when including this in the Source file...
#include <assimp/Importer.hpp>
#include <assimp/scene.h>
#include <assimp/postprocess.h>

#include "../Scene/ModelLoader.h"

std::vector<Model::Texture> textures_loaded;

static Assimp::Importer import;

float vertices[] = {
     0.5f,  0.5f, 0.0f,  // top right
     0.5f, -0.5f, 0.0f,  // bottom right
    -0.5f, -0.5f, 0.0f,  // bottom left
    -0.5f,  0.5f, 0.0f   // top left
};
unsigned int indices[] = {  // note that we start from 0!
    0, 1, 3,  // first Triangle
    1, 2, 3   // second Triangle
};

ModelLoader::ModelLoader()
{

}

void ModelLoader::loadModel(std::string path) {
	const aiScene *scene = import.ReadFile(path, aiProcess_FlipUVs);

	if (!scene || (scene->mFlags & AI_SCENE_FLAGS_INCOMPLETE) || !scene->mRootNode) {
		std::cout << "ERROR::ASSIMP::" << import.GetErrorString() << std::endl;
		return;
	}

	processNode(scene->mRootNode, scene);
}

void ModelLoader::processNode(aiNode *node, const aiScene *scene) {
	// process all the node's meshes (if any)
	for (unsigned int i = 0; i < node->mNumMeshes; i++) {
		aiMesh *mesh = scene->mMeshes[node->mMeshes[i]];
		currentScene.models.push_back(processModel(mesh, scene));
	}
	// then do the same for each of its children
	for (unsigned int i = 0; i < node->mNumChildren; i++) {
		processNode(node->mChildren[i], scene);
	}
}

Model ModelLoader::processModel(aiMesh *mesh, const aiScene *scene)
{
	Model model(mesh, scene);
	return model;
}

ModelLoader::~ModelLoader() {
	// TODO Auto-generated destructor stub
}

