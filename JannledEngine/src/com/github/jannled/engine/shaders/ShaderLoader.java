package com.github.jannled.engine.shaders;

import java.io.File;

import com.github.jannled.engine.maths.Matrix4f;
import com.github.jannled.lib.Print;
import com.jogamp.opengl.GL4;

public class ShaderLoader
{
	GL4 gl;
	
	final String vertexShader = "/com/github/jannled/engine/shaders/VertexShader.glsl";
	final String fragmentShader = "/com/github/jannled/engine/shaders/FragmentShader.glsl";
	
	Shader[] shaders;
	Shaderprogram shaderprogram;
	int[] matrixIDs;
	
	public ShaderLoader(GL4 gl4)
	{
		this.gl = gl4;
		File testDefaultLoc = new File("");
		Print.m("Working location: " + testDefaultLoc.getAbsolutePath() + ".");
	}
	
	public void loadShaders()
	{
		shaders = new Shader[2];
		shaders[0] = new Shader(gl, GL4.GL_VERTEX_SHADER, vertexShader);
		shaders[1] = new Shader(gl, GL4.GL_FRAGMENT_SHADER, fragmentShader);
	}
	
	public void createShaderProgram()
	{
		matrixIDs = new int[2];
		shaderprogram = new Shaderprogram(gl, shaders[0], shaders[1]);
		matrixIDs[0] = createMatrix(shaderprogram.getID(), "transform");
		gl.glUseProgram(shaderprogram.getID());
	}
	
	public int createMatrix(int shaderProgramID, String name)
	{
		int handle = gl.glGetUniformLocation(shaderProgramID, name);
		Print.m("Created Matrix ID " + handle + ".");
		return handle;
	}
	
	public void updateMatrix(int matrixHandle, Matrix4f matrix)
	{
		gl.glUniformMatrix4fv(matrixHandle, 1, true, matrix.toFloatBuffer());
	}
	
	public Shader[] getShaders()
	{
		return shaders;
	}
	
	public Shaderprogram getShaderProgram()
	{
		return shaderprogram;
	}
}
