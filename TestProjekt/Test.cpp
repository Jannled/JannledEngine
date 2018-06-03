/*
 * Test.cpp
 *
 *  Created on: 08.05.2018
 *	  Author: Jannled
 */

#include <iostream>
#include <GL/glew.h>
#include <GLFW/glfw3.h>

#include "Shader/Shader.h"
#include "Shader/ShaderProgram.h"
#include "Debug.h"
#include "Scene/Model.h"
#include "Scene/ModelLoader.h"

//TODO Header does not compile: #include "Test.h"

using namespace std;

const char *vertexShaderPath = "Shader/vertexShader.glsl";
const char *fragmentShaderPath = "Shader/fragmentShader.glsl";

ShaderProgram loadShader()
{
	Shader vertexShader (vertexShaderPath, GL_VERTEX_SHADER);
	Shader fragmentShader (fragmentShaderPath, GL_FRAGMENT_SHADER);
	Shader shaders[] = {vertexShader, fragmentShader};
	ShaderProgram shaderProgram (shaders, 2);
	shaderProgram.useProgram();
	return shaderProgram;
}

int main(int argc, char *argv[])
{
	//Print command line args
	cout << "Starting Test Project with arguments: ";
	for(int i=0; i<argc; i++)
	{
		cout << argv[i] << ((i==argc-1) ? "\n" : ", ");
	}

	//Initialize GLFW
	GLenum glfwFlag = glfwInit();
	if(!glfwFlag)
	{
		cerr << "GLFW initialization failed!";
	}

	glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4);
	glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
	glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
	//glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);

	GLFWwindow* window = glfwCreateWindow(1280, 720, "LearnOpenGL", NULL, NULL);
	if (window == NULL)
	{
		std::cout << "Failed to create GLFW window" << std::endl;
		glfwTerminate();
		return -1;
	}
	glfwMakeContextCurrent(window);

	//Initialize OpenGL/GLEW
	GLenum glewflag = glewInit();
	if(glewflag != GLEW_OK)
	{
		cerr << "Glew failed to initialize: " << glewGetErrorString(glewflag) << endl;
	}

	//Debug Output
	glEnable(GL_DEBUG_OUTPUT);
	glDebugMessageCallback(Debug::MessageCallback, 0);
	Debug::printDebugInfos();

	glViewport(0, 0, 1280, 720);

	cout << "OpenGL initialization phase complete, engines should be up and running" << endl;

	ModelLoader ml;
	ml.loadModel("C:\\Users\\jannl\\git\\JannledEngine\\TestProjekt\\Scene\\Suzanna.obj");
	ShaderProgram program = loadShader();

	glClearColor(0, 0, 0, 0);

	//Start render loop
	while(!glfwWindowShouldClose(window))
	{
		glClear(GL_COLOR_BUFFER_BIT);

		ml.currentScene.meshes[0].render(program);

        glfwSwapBuffers(window);
    	glfwPollEvents();
	}

	std::cout << "Closing application.";
	glfwTerminate();

	return 0;
}
