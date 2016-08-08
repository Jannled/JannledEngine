package com.github.jannled.engine.shaders;

import java.io.File;
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
		Print.m("Done! Created Shader Program ID " + shaderProgramID + ".");
	}
	
	public String readFromFile(String file)
	{
		String s = File.separator;
		String replace = s;
		if(s.equals("\\"))
			replace = "\\\\";
		String path = file.replaceAll("/", replace);
		
		File shaderFile = new File("." + path);
		
		//Check if still in Workspace
		if(!shaderFile.exists())
		{
			Print.m("Still in WorkSpace!");
			path = s + "src" + path;
			shaderFile = new File("." + path);
		}
		Print.m("Loading Shader " + path + ".");
		String output = FileUtils.readTextFileN(shaderFile);
		return output;
	}
	
	public int loadShader(int type, String file)
	{
		int shaderID = gl.glCreateShader(type);
		String[] files = {readFromFile(file)};
		gl.glShaderSource(shaderID, 1, files, null);
		gl.glCompileShader(shaderID);
		getErrorMsg(shaderID);
		
		return shaderID;
	}
	
	public int createProgram(int vertexShaderID, int fragmentShaderID)
	{
		int shaderProgramID = gl.glCreateProgram();
		gl.glAttachShader(shaderProgramID, vertexShaderID);
		gl.glAttachShader(shaderProgramID, fragmentShaderID);
		gl.glLinkProgram(shaderProgramID);
		this.shaderProgramID = shaderProgramID;
		return shaderProgramID;
	}
	
	public String getErrorMsg(int shaderID)
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
			System.out.println("	" + text);
		}
		return null;
	}
}
