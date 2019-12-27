//Thanks to nickrolfe: https://gist.github.com/nickrolfe/1127313ed1dbf80254b614a721b3ee9c

#ifndef WINDOW_H
#define WINDOW_H


#include <windows.h>

//wglCreateContextAttribsARB_type( ... );
typedef HGLRC WINAPI wglCreateContextAttribsARB_type(HDC hdc, HGLRC hShareContext, const int *attribList);

// See https://www.opengl.org/registry/specs/ARB/wgl_create_context.txt for all values
#define WGL_CONTEXT_MAJOR_VERSION_ARB             0x2091
#define WGL_CONTEXT_MINOR_VERSION_ARB             0x2092
#define WGL_CONTEXT_PROFILE_MASK_ARB              0x9126

#define WGL_CONTEXT_CORE_PROFILE_BIT_ARB          0x00000001

//wglChoosePixelFormatARB_type( ... );
typedef BOOL WINAPI wglChoosePixelFormatARB_type(HDC hdc, const int *piAttribIList, const FLOAT *pfAttribFList, UINT nMaxFormats, int *piFormats, UINT *nNumFormats);

// See https://www.opengl.org/registry/specs/ARB/wgl_pixel_format.txt for all values
#define WGL_DRAW_TO_WINDOW_ARB                    0x2001
#define WGL_ACCELERATION_ARB                      0x2003
#define WGL_SUPPORT_OPENGL_ARB                    0x2010
#define WGL_DOUBLE_BUFFER_ARB                     0x2011
#define WGL_PIXEL_TYPE_ARB                        0x2013
#define WGL_COLOR_BITS_ARB                        0x2014
#define WGL_DEPTH_BITS_ARB                        0x2022
#define WGL_STENCIL_BITS_ARB                      0x2023

#define WGL_FULL_ACCELERATION_ARB                 0x2027
#define WGL_TYPE_RGBA_ARB                         0x202B

class Window
{
	public:
		static void init(const char *windowTitle, HINSTANCE inst, int show);
		static void destroy();
		static void fatal_error(char *msg);
		static void (*pre)();
		static void (*render)();
		
		static bool running;
		
	protected:
		static const char *windowTitle;
		
		//Windows related stuff
		static void init_opengl_extensions(void);
		static HGLRC init_opengl(HDC real_dc);
		static HWND create_window(const char *windowTitle, HINSTANCE inst);
		static LRESULT CALLBACK window_callback(HWND window, UINT msg, WPARAM wparam, LPARAM lparam);
};

#endif //WINDOW_H