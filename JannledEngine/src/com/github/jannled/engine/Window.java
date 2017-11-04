package com.github.jannled.engine;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

import com.github.jannled.lib.Print;

public class Window
{
	private long window = -1;
	private Renderloop renderer;
	
	private String title;
	private int width;
	private int height;
	private int exitState;
	
	public Window(String name, int width, int height)
	{
		this.title = name;
		this.width = width;
		this.height = height;
		
		if(glfwInit())
		{
			init();
			Print.m("GLFW sucessfully initialized.");
			
			//Loop until close is requested
			while(!glfwWindowShouldClose(window))
			{
				loop();
			}
			
			//Free resources when window gets closed
			glfwFreeCallbacks(window);
			glfwDestroyWindow(window);
			glfwTerminate();
			glfwSetErrorCallback(null).free();
			System.exit(exitState);
		}
		else
		{
			Print.e("GLFW creation failed, window cannot be openend. Exiting now!");
			exitState = -1;
			System.exit(exitState);
		}
	}
	
	public void init()
	{
		window = glfwCreateWindow(width, height, title, NULL, NULL);
		
		GLFWErrorCallback.createPrint(System.err).set();
		
		try ( MemoryStack stack = stackPush() ) {
			IntBuffer pWidth = stack.mallocInt(1); // int*
			IntBuffer pHeight = stack.mallocInt(1); // int*

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
		
		glfwMakeContextCurrent(window);
		GL.createCapabilities();
		glClearColor(1.0f, 0.0f, 1.0f, 0.0f);
		
		renderer = new Renderloop(window);
	}
	
	public void loop()
	{
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glfwPollEvents();
		renderer.renderFrame();
		glfwSwapBuffers(window);
	}
	
	/**
	 * Get the class that contains the code to loop each frame.
	 * @return The Renderer.
	 */
	public Renderloop getRenderer()
	{
		return renderer;
	}
}
