/*
 * Debug.h
 *
 *  Created on: 18.05.2018
 *      Author: Jannled
 */

#ifndef DEBUG_H_
#define DEBUG_H_

#include <iostream>
#include <GL/glew.h>
#include <GLFW/glfw3.h>

class Debug {
public:
	static void GLAPIENTRY
	MessageCallback(GLenum source, GLenum type, GLuint id, GLenum severity, GLsizei length, const GLchar* message, const void* userParam);
	static std::string getOsName();
	static void printDebugInfos();
};

#endif /* DEBUG_H_ */
