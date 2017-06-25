package com.github.jannled.engine;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Window
{
	private long window = -1;
	
	public Window(String name, int width, int height)
	{
		init(name, width, height);
		loop();
	}
	
	public void init(String name, int width, int height)
	{
		//Stream errors to System.err
		GLFWErrorCallback.createPrint(System.err).set();
		
		//Initialize the GLFW
		if ( !glfwInit() ) throw new IllegalStateException("Unable to initialize GLFW");
		
		//Set default window Hints
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
		
		//Create the window
		window = glfwCreateWindow(width, height, name, NULL, NULL);
		if ( window == NULL ) throw new RuntimeException("Failed to create the GLFW window");
	}
	
	public void loop()
	{
		
	}
	
	public void destroy()
	{
		glfwFreeCallbacks(window);
	}
}
