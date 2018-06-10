/*
 * Test.cpp
 *
 *  Created on: 08.05.2018
 *	  Author: Jannled
 */

#include "Window.h"

using namespace std;

static Window* instance;

const char *vertexShaderPath = "Shader/vertexShader.glsl";
const char *fragmentShaderPath = "Shader/fragmentShader.glsl";

void window_size_callback(GLFWwindow* window, int width, int height)
{
	cout << "New window size: " << width << "x" << height << endl;
	glViewport(0, 0, width, height);

	//Setup projection matrix
	glm::mat4 projm = glm::perspective(glm::radians(60.0f), (float)width/(float)height, 0.1f, 1000.0f);
	instance->activeProgram->acticveProjection = projm;
}

Window::Window(string title)
{
	//Initialize GLFW
	instance = this;
	GLenum glfwFlag = glfwInit();
	if(!glfwFlag)
	{
		cerr << "GLFW initialization failed!";
	}

	glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4);
	glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
	glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
	//glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);

	applicationWindow = glfwCreateWindow(1280, 720, "LearnOpenGL", NULL, NULL);
	if (applicationWindow == NULL)
	{
		std::cout << "Failed to create GLFW window" << std::endl;
		glfwTerminate();
		exit(-1);
	}
	glfwMakeContextCurrent(applicationWindow);

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
	glfwSetWindowSizeCallback(applicationWindow, window_size_callback);

	cout << "OpenGL initialization phase complete, engines should be up and running" << endl;

	ModelLoader ml;
	ml.loadModel("C:\\Users\\jannl\\git\\JannledEngine\\TestProjekt\\Scene\\Suzanna.obj");

	Shader vertexShader (vertexShaderPath, GL_VERTEX_SHADER);
	Shader fragmentShader (fragmentShaderPath, GL_FRAGMENT_SHADER);
	Shader shaders[] = {vertexShader, fragmentShader};
	activeProgram = new ShaderProgram(shaders, 2);
	activeProgram->useProgram();

	glClearColor(0, 0, 0, 0);

	window_size_callback(applicationWindow, 1280, 720); //Resize fix

	//Start render loop
	while(!glfwWindowShouldClose(applicationWindow))
	{
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		ml.currentScene.render(*activeProgram);

		glfwSwapBuffers(applicationWindow);
		glfwPollEvents();
	}

	std::cout << "Closing application.";
	glfwTerminate();
}

Window::~Window()
{
	//TODO Empty Method destructor
}

