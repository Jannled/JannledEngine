package com.github.jannled.engine.shader;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
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
	public Shader(int type, String path)
	{
		this.shaderType = type;
		this.shaderID = loadShader(type, path);
	}
	
	private int loadShader(int type, String path)
	{
		String cont = readFromFile(path);
		int shaderID = glCreateShader(type);
		glShaderSource(shaderID, cont);
		glCompileShader(shaderID);
		getShaderErrorMsg(shaderID);
		
		return shaderID;
	}
	
	private static String readFromFile(String filePath)
	{
		URL url = Shader.class.getResource(filePath);
		File file;
		try
		{
			file = new File(url.toURI());
			Print.m("Loading Shader " + file.getAbsolutePath() + ".");
			String output = FileUtils.readTextFileN(file);
			return output;
		} catch (URISyntaxException e)
		{
			Print.e("Invalid URL " + url.toString());
			e.printStackTrace();
		}
		return null;
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
