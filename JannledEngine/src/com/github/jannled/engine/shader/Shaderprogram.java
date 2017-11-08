package com.github.jannled.engine.shader;

import static org.lwjgl.opengl.GL20.*;

import com.github.jannled.lib.Print;

public class Shaderprogram
{
	private Shader[] shaders;
	private int programID;
	
	/**
	 * Create a new Shaderprogram
	 * @param shader All Shaders that should be linked together.
	 */
	public Shaderprogram(Shader... shader)
	{
		this.shaders = shader;
		
		programID = glCreateProgram();
		for(Shader s : shader)
		{
			glAttachShader(programID, s.getShaderID());
		}
		glLinkProgram(programID);
		Print.m("Linked " + shader.length + " Shaders and created Shaderprogram with ID " + programID + ".");
	}

	public Shader[] getShaders()
	{
		return shaders;
	}

	public int getProgramID()
	{
		return programID;
	}
}
