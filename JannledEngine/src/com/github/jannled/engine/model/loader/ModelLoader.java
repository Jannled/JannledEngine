package com.github.jannled.engine.model.loader;

import java.io.File;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Vector;

import com.github.jannled.engine.constants.OBJ;
import com.github.jannled.engine.constants.VAO;
import com.github.jannled.engine.model.Material;
import com.github.jannled.engine.model.Model;
import com.github.jannled.lib.FileUtils;
import com.github.jannled.lib.Print;
import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.GL4;

/**
 * Class for loading models into memory
 * @author Jannled
 * @version 0.0.2
 */
public class ModelLoader
{
	GL4 gl;
	ModelFileLoader fileLoader = new ModelFileLoader();
	private Vector<Integer> vaos = new Vector<Integer>();
	private Vector<Integer> vbos = new Vector<Integer>();
	
	public ModelLoader(GL4 gl)
	{
		this.gl = gl;
	}
	
	public Model load(File file)
	{
		if(file.exists())
		{
			String[] text = FileUtils.readTextFile(file);
			return loadToGPU(text);
		}
		else 
		{
			Print.e("Unable to find model file " + file.getAbsolutePath() + "!");
			return null;
		}
	}
	
	public Model loadToGPU(String[] text)
	{
		String name = fileLoader.getData(text, OBJ.NAME)[0];
		float[] vertexData = fileLoader.getVertexData(text, OBJ.VERTICE);
		float[] colorData = new float[vertexData.length];
		int[] indiceData = fileLoader.getIndiceData(text, OBJ.FACE);
		Material material = new Material("Random");
		float[] textureCoords = fileLoader.getVertexData(text, OBJ.TEXTURECOORDINATE);
		
		int[] vbos = {createVBO(vertexData), createVBO(colorData)};
		int ibo = createIBO(indiceData);
		int vaoID = createVAO(vbos, new int[] {VAO.POSITION_INDEX, VAO.COLOR_INDEX});
		Model model = new Model(vaoID, ibo, name, vertexData, indiceData, colorData, material, textureCoords);
		return model;
	}
	
	public int createVBO(float[] data)
	{
		int[] vbo = new int[1];
		long len = data.length * 4;
		gl.glGenBuffers(1, vbo, 0);
		gl.glBindBuffer(GL4.GL_ARRAY_BUFFER, vbo[0]);
		gl.glBufferData(GL4.GL_ARRAY_BUFFER, len, loadToBuffer(data), GL4.GL_STATIC_DRAW);
		
		vbos.add(vbo[0]);
		Print.m("Created VBO ID " + vbo[0] + ".");
		return vbo[0];
	}
	
	public int createIBO(int[] data)
	{
		int[] ibo = new int[1];
		long len = data.length;
		gl.glGenBuffers(1, ibo, 0);
		gl.glBindBuffer(GL4.GL_ELEMENT_ARRAY_BUFFER, ibo[0]);
		gl.glBufferData(GL4.GL_ELEMENT_ARRAY_BUFFER, len, loadToBuffer(data), GL4.GL_STATIC_DRAW);
		Print.m("Created IBO ID " + ibo[0] + ".");
		return ibo[0];
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
		gl.glBindVertexArray(0);
		return vao[0];
	}
	
	/**
	 * Load the float-array of positions to a float-buffer
	 * @param data
	 * @return
	 */
	public FloatBuffer loadToBuffer(float[] data)
	{
		FloatBuffer buffer = Buffers.newDirectFloatBuffer(data);
		return buffer;
	}
	
	/**
	 * Load the int-array of positions to a float-buffer
	 * @param data
	 * @return
	 */
	public IntBuffer loadToBuffer(int[] data)
	{
		IntBuffer buffer = Buffers.newDirectIntBuffer(data);
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
	
	public float[] generateColors(int len)
	{
		float[] colors = new float[len];
		for(int i=0; i<len; i++)
		{
			colors[i] = 0.0F;
		}
		return colors;
	}
}
