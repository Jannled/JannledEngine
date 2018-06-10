/*
 * Window.h
 *
 *  Created on: 08.06.2018
 *	  Author: Jannled
 */

#include <iostream>

#include <GL/glew.h>
#include <GLFW/glfw3.h>
#include <glm/glm.hpp>

#include "Shader/Shader.h"
#include "Shader/ShaderProgram.h"
#include "Debug.h"
#include "Scene/Model.h"
#include "Scene/ModelLoader.h"

#include "Scene/Model.h"

class Window
{
public:
	ShaderProgram* activeProgram;
	GLFWwindow* applicationWindow;

	Window(std::string);

	void windowSizeCallback(GLFWwindow* window, int width, int height);

	void processInput(GLFWwindow);
	void errorCallback(int, const char*);

	virtual ~Window();
};
