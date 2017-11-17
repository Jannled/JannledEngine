package com.github.jannled.test.testengine;

import static org.lwjgl.glfw.GLFW.*;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import org.lwjgl.opengl.GL;

import com.github.jannled.engine.scene.Model;
import com.github.jannled.engine.shader.Shader;
import com.github.jannled.engine.shader.Shaderprogram;
import com.github.jannled.lib.math.Vector;

public class Test
{
	long window;
	Model m;
	
	public static final float[] triangle =
		{
			-1.0f, -1.0f, 0.0f,
			1.0f, -1.0f, 0.0f,
			0.0f, 1.0f, 0.0f
		};
	
	public static final String vshader = ""
			+ "#version 330 core \n"
			+ "layout(location = 0) in vec3 vertexPosition_modelspace; \n"
			+ "void main(){"
			+ "gl_Position.xyz = vertexPosition_modelspace; "
			+ "gl_Position.w = 1.0;"
			+ "}";
	
	public static final String fshader = ""
			+ "#version 330 core\n"
			+ "out vec3 color;\n"
			+ "void main(){\n"
			+ "  color = vec3(1,0,0);\n" 
			+ "}";
	
	private Test()
	{
		if(!glfwInit()) System.exit(-1);
		
		glfwWindowHint(GLFW_SAMPLES, 4); // 4x antialiasing
		//glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3); // We want OpenGL 3.3
		//glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
		//glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE); // To make MacOS happy; should not be needed
		//glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
		
		window = glfwCreateWindow(1280, 720, "The Test", 0, 0);
		if(window==0) System.exit(-1);
		glfwMakeContextCurrent(window);
		GL.createCapabilities();
		
		init();
		
		while(!glfwWindowShouldClose(window))
		{
			loop();
		}
	}
	
	public static void main(String[] args)
	{
		new Test();
	}

	public void init()
	{
		m = new Model(new Vector(3), triangle);
		//m.upload();
		
		Shader vs = new Shader(GL_VERTEX_SHADER, vshader);
		Shader fs = new Shader(GL_FRAGMENT_SHADER, fshader);
		Shaderprogram sp = new Shaderprogram(vs, fs);
		
		glUseProgram(sp.getProgramID());
	}
	
	public void loop()
	{
		glfwSwapBuffers(window);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	    glfwPollEvents();
	    m.render();
	}
}
