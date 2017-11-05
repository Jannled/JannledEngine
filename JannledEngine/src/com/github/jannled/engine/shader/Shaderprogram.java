package com.github.jannled.engine.shader;

import static org.lwjgl.opengl.GL20.*;

public class Shaderprogram
{
	private Shader[] shaders;
	private int programeID;
	
	/**
	 * Create a new Shaderprogram
	 * @param shader All Shaders that should be linked together.
	 */
	public Shaderprogram(Shader... shader)
	{
		this.shaders = shader;
		
		programeID = glCreateProgram();
		for(Shader s : shader)
		{
			glAttachShader(programeID, s.getShaderID());
		}
		glLinkProgram(programeID);
	}

	public Shader[] getShaders()
	{
		return shaders;
	}

	public int getProgrameID()
	{
		return programeID;
	}
}
