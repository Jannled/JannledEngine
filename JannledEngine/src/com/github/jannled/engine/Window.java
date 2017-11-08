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

/**
 * Create a new window containing the renderer.<br>
 * Note: This class has to be run on the main thread and is blocking it, running the rest of the program in a separate Thread is a good idea.
 * But all GL calls need to be done on the main Thread. Use the dedicated GPUUploader for resources, and load the Shaders in the Renderlooper interface.
 * @author Jannled
 */
public class Window
{
	private long window = -1;
	private Renderer renderer;
	private Renderlooper renderlooper;
	
	private String title;
	private int width;
	private int height;
	private int exitState;
	
	public Window(Renderlooper renderlooper, String name, int width, int height)
	{
		this.title = name;
		this.width = width;
		this.height = height;
		this.renderlooper = renderlooper;
		
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
			Print.m("Shutting down GLFW...");
			glfwFreeCallbacks(window);
			glfwDestroyWindow(window);
			glfwTerminate();
			//TODO figure out why it throws a nullpointer: glfwSetErrorCallback(null).free();
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
		
		//TODO Error callbacks leak bytes and cause Nullpointer on shutdown: GLFWErrorCallback.createPrint(System.err).set();
		
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
		
		renderer = new Renderer(renderlooper);
	}
	
	public void loop()
	{
		glfwMakeContextCurrent(window);
		glfwPollEvents();
		renderer.renderFrame();
		glfwSwapBuffers(window);
	}
	
	/**
	 * Get the class that contains the code to loop each frame.
	 * @return The Renderer.
	 */
	public Renderer getRenderer()
	{
		return renderer;
	}
	
	public int getWindowID()
	{
		return (int) window;
	}
}
