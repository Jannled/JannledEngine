package com.github.jannled.engine.shader;

import java.io.File;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;

import com.github.jannled.lib.FileUtils;
import com.github.jannled.lib.Print;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

public class Shader
{
	private int shaderType;
	private int shaderID;
	
	/**
	 * Class representing a shader
	 * @param type Usually <b>GL_VERTEX_SHADER</b> or <b>GL_FRAGMENT_SHADER</b>
	 * @param path Path to the Shader.glsl file
	 */
	public Shader(int type, File file)
	{
		this.shaderType = type;
		this.shaderID = loadShader(type, file);
	}
	
	private int loadShader(int type, File file)
	{
		String cont = readFromFile(file);
		int shaderID = glCreateShader(type);
		glShaderSource(shaderID, cont);
		glCompileShader(shaderID);
		getShaderErrorMsg(shaderID);
		
		return shaderID;
	}
	
	private static String readFromFile(File file)
	{
		Print.m("Loading Shader " + file.getAbsolutePath() + ".");
		String output = FileUtils.readTextFileN(file);
		return output;
	}
	
	private String getShaderErrorMsg(int shaderID)
	{
		IntBuffer compilerStat = BufferUtils.createIntBuffer(1);
		String log = glGetShaderInfoLog(shaderID);
		
		glGetShaderiv(shaderID, GL_COMPILE_STATUS, compilerStat);
		if(compilerStat.get(0) == GL_TRUE)
		{
			Print.m("Shader ID " + shaderID + " compiled sucessfull.");
		}
		else
		{
			Print.e("Compilation of Shader " + shaderID + " Failed with error Message: ");
			System.err.println("	" + log);
		}
		return log;
	}
	
	public int getShaderType()
	{
		return shaderType;
	}
	
	public int getShaderID()
	{
		return shaderID;
	}
}
