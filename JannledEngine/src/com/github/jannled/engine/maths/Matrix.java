package com.github.jannled.engine.maths;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

/**
 * A float matrix.
 * @author Jannled
 */
public class Matrix
{
	private float[] values;
	private int width;
	private int height;
	
	/**
	 * Create an empty matrix with the given dimensions.
	 * @param width The width of the matrix.
	 * @param height The height of the matrix.
	 */
	public Matrix(int width, int height)
	{
		values = new float[width * height];
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Create a matrix with the given values, they are stored as a one dimensional array, the with and height of the matrix are stored in seperate values.
	 * @param values The values to initialize the matrix with.
	 * @param width The width of the matrix.
	 * @param height The height of the matrix.
	 */
	public Matrix(float[] values, int width, int height)
	{
		this.values = values;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Not recommend because the vector is stored in a one dimensional array, so the two dimensional vector needs to be transformed. 
	 * @param values The values for the matrix
	 */
	public Matrix(float[][] values)
	{
		width = values.length;
		height = values[0].length;
		
	}
	
	/**
	 * Create a new identity matrix.
	 * @param width The width of the identity matrix.
	 * @param height The height of the identity matrix.
	 * @return The identity matrix.
	 */
	public static Matrix identity(int width, int height)
	{
		Matrix mout = new Matrix(width, height);
		int count = (width < height) ? height : width;
		for(int i=0; i<count; i++)
		{
			mout.set(i, i, 1);
		}
		return mout;
	}

	/**
	 * Get the dimension of the matrix
	 * @return The first value is the width, the second value is the height. 
	 */
	public int[] getDimension()
	{
		return new int[] {width, height};
	}
	
	/**
	 * @return A Floatbuffer with the matrix in it.
	 */
	public FloatBuffer toFloatBuffer()
	{
		FloatBuffer fb = BufferUtils.createFloatBuffer(values.length);
		fb.put(values);
		return fb;
	}
	
	/**
	 * Set a value in the matrix.
	 * @param xpos The x position of the value to alter.
	 * @param ypos The y position of the value to alter.
	 * @param value The value to alter.
	 */
	public void set(int xpos, int ypos, float value)
	{
		values[xpos * ypos] = value;
	}
}