package com.github.jannled.engine.shaders;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import com.github.jannled.lib.FileUtils;
import com.github.jannled.lib.Print;
import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.GL4;

public class Shader
{
	private GL4 gl;
	private int shaderType;
	private int shaderID;
	
	/**
	 * 
	 * @param gl The instance of OpenGL
	 * @param type Usually <b>GL_VERTEX_SHADER</b> or <b>GL_FRAGMENT_SHADER</b>
	 * @param path Path to the Shader.glsl file
	 */
	public Shader(GL4 gl, int type, String path)
	{
		this.gl = gl;
		this.shaderType = type;
		this.shaderID = loadShader(type, path);
	}
	
	private int loadShader(int type, String file)
	{
		String[] files = {readFromFile(file)};
		int shaderID = gl.glCreateShader(type);
		gl.glShaderSource(shaderID, 1, files, null);
		gl.glCompileShader(shaderID);
		getShaderErrorMsg(shaderID);
		
		return shaderID;
	}
	
	private static String readFromFile(String filePath)
	{
		URL url = ShaderLoader.class.getResource(filePath);
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
		IntBuffer compilerStat = Buffers.newDirectIntBuffer(1);
		gl.glGetShaderiv(shaderID, GL4.GL_COMPILE_STATUS, compilerStat);
		if(compilerStat.get(0) == GL4.GL_TRUE)
		{
			Print.m("Shader ID " + shaderID + " compiled sucessfull.");
		}
		else 
		{
			IntBuffer errorLength = Buffers.newDirectIntBuffer(1);
			ByteBuffer errorMsg; 
			
			gl.glGetShaderiv(shaderID, GL4.GL_INFO_LOG_LENGTH, errorLength);
			errorMsg = Buffers.newDirectByteBuffer(errorLength.get(0));
			
			gl.glGetShaderInfoLog(shaderID, errorLength.get(0), errorLength, errorMsg);
			
			//Make text from bytebuffer
			byte[] code = new byte[errorLength.get(0)];
			for(int i=0; i<errorLength.get(0); i++)
			{
				code[i] = errorMsg.get(i);
			}
			String text = new String(code);
			
			Print.e("Compilation of Shader " + shaderID + " Failed with error Message: ");
			System.err.println("	" + text);
		}
		return null;
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
