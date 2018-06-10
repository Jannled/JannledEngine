/*
 * TestProjekt.cpp
 *
 *  Created on: 08.06.2018
 *      Author: Jannled
 */

#include <iostream>

#include "Window.h"

int main(int argc, char *argv[])
{
	//Print command line args
	std::cout << "Starting Test Project with arguments: ";

	for(int i=0; i<argc; i++)
	{
		std::cout << argv[i] << ((i==argc-1) ? "\n" : ", ");
	}
	std::cout << std::endl;

	Window window("JannledEngine");
	return 0;
}
