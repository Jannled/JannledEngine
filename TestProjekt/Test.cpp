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
#include "Model/Model.h"
#include "Debug.h"

using namespace std;

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

const char *vertexShaderPath = "Shader/vertexShader.glsl";
const char *fragmentShaderPath = "Shader/fragmentShader.glsl";

void loadModel()
{
	Model model(&vertices, 0, &indices, 0);
}

void loadShader()
{
	Shader vertexShader (vertexShaderPath, GL_VERTEX_SHADER);
	Shader fragmentShader (fragmentShaderPath, GL_FRAGMENT_SHADER);
	Shader shaders[] = {vertexShader, fragmentShader};
	ShaderProgram shaderProgram (shaders, 2);
	shaderProgram.useProgram();
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

	loadModel();
	loadShader();

	//glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);

	glClearColor(0, 0, 0, 0);

	//Start render loop
	while(!glfwWindowShouldClose(window))
	{
		glClear(GL_COLOR_BUFFER_BIT);

		glBindVertexArray(model.vaoID);
        glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_INT, 0);
        glfwSwapBuffers(window);
    	glfwPollEvents();
	}

	std::cout << "Closing application.";
	glfwTerminate();

	return 0;
}
