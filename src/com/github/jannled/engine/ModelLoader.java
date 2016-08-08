package com.github.jannled.engine;

import java.nio.FloatBuffer;
import java.util.Vector;

import com.github.jannled.lib.ArrayUtils;
import com.github.jannled.lib.Print;
import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.GL4;

/**
 * 
 * @author Jannled
 * @version 0.0.1
 */
public class ModelLoader
{
	GL4 gl;
	private Vector<Integer> vaos = new Vector<Integer>();
	private Vector<Integer> vbos = new Vector<Integer>();
	
	public ModelLoader(GL4 gl)
	{
		this.gl = gl;
	}
	
	public int load(float[][] data)
	{
		int[] vbos = {createVBO(data)};
		int vaoID = createVAO(vbos, new int[] {0});
		return vaoID;
	}
	
	public int createVBO(float[][] data)
	{
		int[] vbo = new int[1];
		long len = data.length * data[0].length * 4;
		gl.glGenBuffers(1, vbo, 0);
		gl.glBindBuffer(GL4.GL_ARRAY_BUFFER, vbo[0]);
		gl.glBufferData(GL4.GL_ARRAY_BUFFER, len, loadToBuffer(data), GL4.GL_STATIC_DRAW);
		
		vbos.add(vbo[0]);
		Print.m("Created VBO ID " + vbo[0] + ".");
		return vbo[0];
	}
	
	/**
	 * Creates a VAO and fills it with the VBOs.
	 * @param positionIndex The index where the next type of data starts in the array. One Index for each VBO.
	 * @return
	 */
	public int createVAO(int[] vbos, int[] positionIndex)
	{
		int[] vao = new int[1];
		gl.glGenVertexArrays(1, vao, 0);
		gl.glBindVertexArray(vao[0]);
		
		for(int i=0; i<vbos.length; i++)
		{
			Print.m("Binding VBO " + vbos[i] + " to VAO " + vao[0] + ".");
			gl.glEnableVertexAttribArray(positionIndex[i]);
			gl.glBindBuffer(GL4.GL_ARRAY_BUFFER, vbos[i]);
			gl.glVertexAttribPointer(positionIndex[i], 3, GL4.GL_FLOAT, false, 0, 0);
		}
		return vao[0];
	}
	
	/**
	 * Load the float-array of positions to a float-buffer
	 * @param data
	 * @return
	 */
	public FloatBuffer loadToBuffer(float[][] data)
	{
		FloatBuffer buffer = Buffers.newDirectFloatBuffer(ArrayUtils.to1DArray(data));
		return buffer;
	}
	
	/**
	 * Getter for the created VAOs so far.
	 * @return An Vector-Array containing all created <b>V</b>ertex <b>A</b>tribute <b>O</b>bjects.
	 */
	public Vector<Integer> getVAOS()
	{
		return vaos;
	}
}
