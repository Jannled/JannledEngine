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

public class ShaderLoader
{
	GL4 gl;
	int shaderProgramID = 0;
	
	public ShaderLoader(GL4 gl4)
	{
		this.gl = gl4;
		File testDefaultLoc = new File("");
		Print.m("Working location: " + testDefaultLoc.getAbsolutePath() + ".");
	}
	
	public void loadShaders(String vertexShader, String fragmentShader)
	{
		Print.m("Loading Shaders...");
		int vertexShaderID = loadShader(GL4.GL_VERTEX_SHADER, vertexShader);
		int fragmentShaderID = loadShader(GL4.GL_FRAGMENT_SHADER, fragmentShader);
		int shaderProgramID = createProgram(vertexShaderID, fragmentShaderID);
		gl.glUseProgram(shaderProgramID);
		Print.m("Done! Created Shader Program ID " + shaderProgramID + ".");
	}
	
	public String readFromFile(String filePath)
	{
		URL uri = ShaderLoader.class.getResource(filePath);
		File file;
		try
		{
			file = new File(uri.toURI());
			Print.m("Loading Shader " + file.getAbsolutePath() + ".");
			String output = FileUtils.readTextFileN(file);
			return output;
		} catch (URISyntaxException e)
		{
			Print.e("Invalid URI " + uri.toString());
			e.printStackTrace();
		}
		return null;
	}
	
	public int loadShader(int type, String file)
	{
		int shaderID = gl.glCreateShader(type);
		String[] files = {readFromFile(file)};
		gl.glShaderSource(shaderID, 1, files, null);
		gl.glCompileShader(shaderID);
		getShaderErrorMsg(shaderID);
		
		return shaderID;
	}
	
	public int createProgram(int vertexShaderID, int fragmentShaderID)
	{
		int shaderProgramID = gl.glCreateProgram();
		gl.glAttachShader(shaderProgramID, vertexShaderID);
		gl.glAttachShader(shaderProgramID, fragmentShaderID);
		gl.glLinkProgram(shaderProgramID);
		getProgramErrorMsg(shaderProgramID);
		gl.glValidateProgram(shaderProgramID);
		this.shaderProgramID = shaderProgramID;
		return shaderProgramID;
	}
	
	public String getShaderErrorMsg(int shaderID)
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
	
	public String getProgramErrorMsg(int programID)
	{
		IntBuffer compilerStat = Buffers.newDirectIntBuffer(1);
		gl.glGetProgramiv(programID, GL4.GL_LINK_STATUS, compilerStat);
		if(compilerStat.get(0) == GL4.GL_TRUE)
		{
			Print.m("Program ID " + programID + " linked sucessfull.");
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
}
