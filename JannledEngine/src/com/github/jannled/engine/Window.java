package com.github.jannled.engine;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.nio.IntBuffer;

public class Window
{
	private long window = -1;
	private Renderloop renderer;
	
	public Window(String name, int width, int height)
	{
		renderer = new Renderloop();
		init(name, width, height);
		loop();
		destroy();
	}
	
	public Window(String name, int width, int height, Renderloop renderer)
	{
		this.renderer = renderer;
		init(name, width, height);
		loop();
		destroy();
	}
	
	public void init(String name, int width, int height)
	{
		//Stream errors to System.err
		GLFWErrorCallback.createPrint(System.err).set();
		
		//Initialize the GLFW
		if ( !glfwInit() ) throw new IllegalStateException("Unable to initialize GLFW");
		
		//Set default window Hints
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_TRUE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
		
		//Create the window
		window = glfwCreateWindow(width, height, name, NULL, NULL);
		if ( window == NULL ) throw new RuntimeException("Failed to create the GLFW window");
		
		//TODO Setup Key Callbacks
		glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
			if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
				glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
		});

		resize();
		
		// Make the OpenGL context current
		glfwMakeContextCurrent(window);
		// Enable v-sync
		glfwSwapInterval(1);
		
		GL.createCapabilities();
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
	}
	
	public void loop()
	{
		while(!glfwWindowShouldClose(window))
		{
			glfwPollEvents();
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			renderer.renderFrame();
			glfwSwapBuffers(window);
		}
	}
	
	public void destroy()
	{
		glfwFreeCallbacks(window);
		glfwDestroyWindow(window);
		glfwTerminate();
		glfwSetErrorCallback(null).free();
	}
	
	public void resize()
	{
		// Get the thread stack and push a new frame
		try ( MemoryStack stack = stackPush() ) {
			IntBuffer pWidth = stack.mallocInt(1);
			IntBuffer pHeight = stack.mallocInt(1);

			// Get the window size passed to glfwCreateWindow
			glfwGetWindowSize(window, pWidth, pHeight);

			// Get the resolution of the primary monitor
			GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

			// Center the window
			glfwSetWindowPos(
				window,
				(vidmode.width() - pWidth.get(0)) / 2,
				(vidmode.height() - pHeight.get(0)) / 2
			);
		} // the stack frame is popped automatically
	}
}
