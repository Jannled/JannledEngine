#include "../lib/GL_4_6_core.h"

#ifdef _WIN32
#define OS_Windows
#include "GLWindow_win.cpp"

#elif __linux__
#define OS_Linux
#include "GLWindow_x11.cpp"

#endif