package com.github.jannled.engine.math;

/**
 * A vector
 * @author Jannled
 * 
 */
public class Vector
{
	float[] vals;
	
	/**
	 * Construct a new vector with zero as value.
	 * @param dimension The number of dimensions the Vector should have.
	 */
	public Vector(int dimension)
	{
		vals = new float[dimension];
	}
	
	/**
	 * Construct a new vector from the array.
	 * @param values The values to use for the new vector.
	 */
	public Vector(float[] values)
	{
		this.vals = values;
	}
	
	/**
	 * Get one value from the vector. 
	 * @param index The index of the value to get (from zero to n).
	 * @return The specified value of the vector.
	 */
	public float getValue(int index)
	{
		return vals[index];
	}
	
	/**
	 * Get all values from the vector.
	 * @return The values are stored in a float array.
	 */
	public float[] getValues()
	{
		return vals;
	}
}
