package com.github.jannled.engine.shaders;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import com.github.jannled.lib.Print;
import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.GL4;

public class Shaderprogram
{
	private GL4 gl;
	private Shader[] shaders;
	private int shaderProgramID;
	
	public Shaderprogram(GL4 gl, Shader vertexShader, Shader fragmentShader)
	{
		Print.m("Creating Shader program!");
		this.gl = gl;
		this.shaderProgramID = gl.glCreateProgram();
		createProgram(vertexShader.getShaderID(), fragmentShader.getShaderID());
		shaders = new Shader[] {vertexShader, fragmentShader};
		getProgramErrorMsg(gl, shaderProgramID);
		gl.glValidateProgram(shaderProgramID);
	}
	
	private void createProgram(int vertexShader, int fragmentShader)
	{
		gl.glAttachShader(shaderProgramID, vertexShader);
		gl.glAttachShader(shaderProgramID, fragmentShader);
		gl.glLinkProgram(shaderProgramID);
	}
	
	private static String getProgramErrorMsg(GL4 gl, int programID)
	{
		IntBuffer compilerStat = Buffers.newDirectIntBuffer(1);
		gl.glGetProgramiv(programID, GL4.GL_LINK_STATUS, compilerStat);
		if(compilerStat.get(0) == GL4.GL_TRUE)
		{
			Print.m("Shaderprogram ID " + programID + " linked sucessfull.");
		}
		else 
		{
			IntBuffer errorLength = Buffers.newDirectIntBuffer(1);
			ByteBuffer errorMsg; 
			
			gl.glGetProgramiv(programID, GL4.GL_INFO_LOG_LENGTH, errorLength);
			errorMsg = Buffers.newDirectByteBuffer(errorLength.get(0));
			
			gl.glGetProgramInfoLog(programID, errorLength.get(0), errorLength, errorMsg);
			
			//Make text from bytebuffer
			byte[] code = new byte[errorLength.get(0)];
			for(int i=0; i<errorLength.get(0); i++)
			{
				code[i] = errorMsg.get(i);
			}
			String text = new String(code);
			
			Print.e("Linking of Shader " + programID + " Failed with error Message: ");
			System.err.println("	" + text);
		}
		return null;
	}
	
	public int getID()
	{
		return shaderProgramID;
	}
	
	public Shader[] getShaders()
	{
		return shaders;
	}
}
