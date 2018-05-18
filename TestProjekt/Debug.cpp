/*
 * Debug.cpp
 *
 *  Created on: 18.05.2018
 *      Author: Jannled
 */

#include "Debug.h"

std::string Debug::getOsName()
{
	#ifdef _WIN64
    return "Windows 64-bit";
    #elif _WIN32
    return "Windows 32-bit";
    #elif __unix || __unix__
    return "Unix";
    #elif __APPLE__ || __MACH__
    return "Mac OSX";
    #elif __linux__
    return "Linux";
    #elif __FreeBSD__
    return "FreeBSD";
    #else
    return "Other";
    #endif
}

void Debug::printDebugInfos()
{
	const unsigned char *glewVersion = glewGetString(GLEW_VERSION);
	const unsigned char *glVersion = glGetString(GL_VERSION);
	const unsigned char *graphicsCard = glGetString(GL_RENDERER);

	std::string debugInfo = "====================================================="
	"\n  Software Arch:	" + getOsName() + ""
	"\n  C++ Version:		" + "GNU C++"
	"\n  GLEW Version:		" + std::string(glewVersion, glewVersion + sizeof glewVersion / sizeof glewVersion[0] + 25) + ""
	"\n  OpenGL Version:	" + std::string(glVersion, glVersion + sizeof glVersion / sizeof glVersion[0] + 25) + ""
	"\n  Graphics Card:	" + std::string(graphicsCard, graphicsCard + sizeof graphicsCard / sizeof graphicsCard[0] + 25) + ""
	"\n=====================================================\n";

	std::cout << debugInfo;
}

void GLAPIENTRY
Debug::MessageCallback(GLenum source, GLenum type, GLuint id, GLenum severity, GLsizei length, const GLchar* message, const void* userParam)
{
	const char *sseverity = severity==GL_DEBUG_SEVERITY_HIGH ? "[HIGH] " :
			severity==GL_DEBUG_SEVERITY_MEDIUM ? "[MEDIUM]" :
			severity==GL_DEBUG_SEVERITY_LOW ? "[LOW]  " :
			severity==GL_DEBUG_SEVERITY_NOTIFICATION ? "[INFO] " : "(UNEXPECTED SEVERITY!)";

	const char *ssource = source==GL_DEBUG_SOURCE_API ? "API" :
			source==GL_DEBUG_SOURCE_WINDOW_SYSTEM ? "SYSTEM" :
			source==GL_DEBUG_SOURCE_SHADER_COMPILER ? "SHADER_COMPILER" :
			source==GL_DEBUG_SOURCE_THIRD_PARTY ? "THIRD_PARTY" :
			source==GL_DEBUG_SOURCE_APPLICATION ? "APPLICATION":
			source==GL_DEBUG_SOURCE_OTHER ? "OTHER" : "(UNEXPECTED SOURCE!)";

	const char *stype = type==GL_DEBUG_TYPE_ERROR ? "ERROR" :
			type==GL_DEBUG_TYPE_DEPRECATED_BEHAVIOR ? "DEPRECATED_BEHAVIOR" :
			type==GL_DEBUG_TYPE_UNDEFINED_BEHAVIOR ? "UNDEFINED_BEHAVIOR" :
			type==GL_DEBUG_TYPE_PORTABILITY ? "PORTABILITY" :
			type==GL_DEBUG_TYPE_PERFORMANCE ? "PERFORMANCE" :
			type==GL_DEBUG_TYPE_MARKER ? "MARKER" :
			type==GL_DEBUG_TYPE_PUSH_GROUP ? "PUSH_GROUP" :
			type==GL_DEBUG_TYPE_OTHER ? "OTHER" : "POP GROUP?";

	std::cerr << "OpenGL: " << sseverity << " (" << ssource << "|" << stype << ") " << message << std::endl;
}
