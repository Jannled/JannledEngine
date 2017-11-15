package com.github.jannled.engine.shader;

import java.io.File;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;

import com.github.jannled.engine.loader.GPUUpload;
import com.github.jannled.lib.FileUtils;
import com.github.jannled.lib.Print;
import com.github.jannled.lib.math.Matrix;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

public class Shader implements GPUUpload
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
	
	/**
	 * Class representing a shader
	 * @param type Usually <b>GL_VERTEX_SHADER</b> or <b>GL_FRAGMENT_SHADER</b>
	 * @param code The source code for the shader.
	 */
	public Shader(int type, String code)
	{
		this.shaderType = type;
		shaderID = glCreateShader(type);
		glShaderSource(shaderID, code);
		glCompileShader(shaderID);
		getShaderErrorMsg();
	}
	
	private int loadShader(int type, File file)
	{
		String cont = readFromFile(file);
		int shaderID = glCreateShader(type);
		glShaderSource(shaderID, cont);
		glCompileShader(shaderID);
		getShaderErrorMsg();
		
		return shaderID;
	}
	
	private static String readFromFile(File file)
	{
		Print.m("Loading Shader " + file.getAbsolutePath() + ".");
		String output = FileUtils.readTextFileN(file);
		return output;
	}
	
	private String getShaderErrorMsg()
	{
		return Shader.getShaderErrorMsg(shaderID);
	}
	
	public static String getShaderErrorMsg(int shaderID)
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
	
	/**
	 * Get the UnifomLocation of an attribute by its name.
	 * @param name The attribute name in the shader.
	 * @return The handle for the specified attribute.
	 */
	public int getAttributeID(String name)
	{
		return glGetUniformLocation(shaderID, name);
	}
	
	/**
	 * Push a matrix to the specified attribute.
	 * @param matrixHandle The handle of the attribute to alter.
	 * @param m The matrix to push.
	 */
	public static void setMatrix(int matrixHandle, Matrix m)
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
		
		glUniformMatrix4fv(matrixHandle, false, fb);
	}

	@Override
	public void toGPU(int vaoID)
	{
		
	}
}
