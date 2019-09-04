#include <stdio.h>
#include <cstdlib>
#include <iostream>
#include <sstream>
#include <fstream>

#include "lib/GL_4_6_core.h"
#include "lib/cgltf.h"

#include "Window.h"

std::string readFile(const char *path);

float vertices[] = {
    -0.5f, -0.5f, 0.0f,
     0.5f, -0.5f, 0.0f,
     0.0f,  0.5f, 0.0f
};



void pre()
{
	unsigned int VBO;
	glGenBuffers(1, &VBO); 
}

void render()
{
	glClearColor(1.0f, 0.5f, 0.5f, 1.0f);
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
}

int WINAPI WinMain(HINSTANCE inst, HINSTANCE prev, LPSTR cmd_line, int show)
{
	printf("Starting program with parameters: [%s]\n", cmd_line);

	//Print OS
	std::cout << "Operating system: ";
	#if defined _WIN32 || _WIN64
		#define FSEP '\\'
		std::cout << "Windows" << std::endl;
	#elif defined __linux__
		#define FSEP '/'
		std::cout << "Linux" << std::endl;
	#elif defined __MACH__ || __APPLE__
		#define FSEP '/'
		std::cout << "Apple" << std::endl;
	#else
		std::cout << "Unknown" << std::endl;
	#endif

	Window::render = render;
	Window::pre = pre;
	Window::init("Best game lol", inst, show);
    return 0;
}

std::string readFile(const char *path)
{
	std::ifstream inFile;
    inFile.open(path); //open the input file

    std::stringstream strStream;
    strStream << inFile.rdbuf(); //read the file
    std::string str = strStream.str(); //str holds the content of the file

   return str;
}
