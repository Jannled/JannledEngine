package com.github.jannled.test.testengine;

import static org.lwjgl.glfw.GLFW.*;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL;

import com.github.jannled.engine.shader.Shader;
import com.github.jannled.lib.Print;

public class Test
{
	long window;
	
	private final float[] triangle =
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
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3); // We want OpenGL 3.3
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
		glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE); // To make MacOS happy; should not be needed
		glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
		
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
		int vao = glGenVertexArrays();
		glBindVertexArray(vao);
		
		int vbo = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, triangle, GL_STATIC_DRAW);
		
		glEnableVertexAttribArray(0);
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
		
		int vsID = glCreateShader(GL_VERTEX_SHADER);
		glShaderSource(vsID, vshader);
		glCompileShader(vsID);
		
		int fsID = glCreateShader(GL_FRAGMENT_SHADER);
		glShaderSource(fsID, fshader);
		glCompileShader(fsID);
		
		//Check if sucessfully
		IntBuffer ib1 = BufferUtils.createIntBuffer(1);
		glGetShaderiv(vsID, GL_COMPILE_STATUS, ib1);
		IntBuffer ib2 = BufferUtils.createIntBuffer(1);
		glGetShaderiv(fsID, GL_COMPILE_STATUS, ib2);
		Print.m("Vertex Shader: " + ib1.get() + ", Fragment Shader: " + ib2.get());
		Shader.getShaderErrorMsg(vsID);
		
		int programID = glCreateProgram();
		glAttachShader(programID, vsID);
		glAttachShader(programID, fsID);
		glLinkProgram(programID);
		
		glUseProgram(programID);
	}
	
	public void loop()
	{
		glfwSwapBuffers(window);
	    glfwPollEvents();
	    
	    glDrawArrays(GL_TRIANGLES, 0, 3);
	}
}
