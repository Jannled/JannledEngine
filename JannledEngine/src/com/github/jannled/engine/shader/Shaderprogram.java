package com.github.jannled.engine.shader;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;

import com.github.jannled.lib.Print;
import com.github.jannled.lib.math.Matrix;

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
		
		getProgramInfoLog();
	}

	public Shader[] getShaders()
	{
		return shaders;
	}

	public int getProgramID()
	{
		return programID;
	}
	
	/**
	 * Get the UnifomLocation of an attribute by its name.
	 * @param name The attribute name in the shader.
	 * @return The handle for the specified attribute.
	 */
	public int getAttributeID(String name)
	{
		return glGetUniformLocation(programID, name);
	}
	
	private String getProgramInfoLog()
	{
		IntBuffer linkStat = BufferUtils.createIntBuffer(1);
		String log = glGetProgramInfoLog(programID);
		
		glGetProgramiv(programID, GL_LINK_STATUS, linkStat);
		if(linkStat.get(0) == GL_TRUE)
		{
			Print.m("Linked " + shaders.length + " Shaders and created Shaderprogram with ID " + programID + ".");
		}
		else
		{
			Print.e("Error while linking the shaders \n\t" + log);
		}
		
		return log;
	}
	
	/**
	 * Push a 4x4 matrix to the specified attribute.
	 * @param matrixHandle The handle of the attribute to alter.
	 * @param m The matrix to push, needs to be a 4x4 matrix!
	 */
	public void setMatrix(int matrixHandle, Matrix m)
	{
		if(m.getWidth() != 4 || m.getHeight() != 4) Print.e("Expected a 4 by 4 matrix, got a " + m.getWidth() + " by " + m.getHeight() + " matrix.");
		FloatBuffer fb = BufferUtils.createFloatBuffer(16);
		
		fb.put((float) m.getValues()[0]);
		fb.put((float) m.getValues()[1]);
		fb.put((float) m.getValues()[2]);
		fb.put((float) m.getValues()[3]);
		fb.put((float) m.getValues()[4]);
		fb.put((float) m.getValues()[5]);
		fb.put((float) m.getValues()[6]);
		fb.put((float) m.getValues()[7]);
		fb.put((float) m.getValues()[8]);
		fb.put((float) m.getValues()[9]);
		fb.put((float) m.getValues()[10]);
		fb.put((float) m.getValues()[11]);
		fb.put((float) m.getValues()[12]);
		fb.put((float) m.getValues()[13]);
		fb.put((float) m.getValues()[14]);
		fb.put((float) m.getValues()[15]);
		fb.flip();
		
		glUniformMatrix4fv(matrixHandle, true, fb);
	}
}
