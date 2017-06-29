package com.github.jannled.engine.maths;

/**
 * A float vector.
 * @author Jannled
 */
public class Vector
{
	private float[] vals;
	
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
	 * Returns the addition of the two vectors.
	 * @param v The Vector to add to the first.
	 * @return The sum of the two vectors or null if the dimension of the two vectors is not equal.
	 */
	public Vector add(Vector v)
	{
		if(getValues().length == v.getValues().length)
		{
			return null;
		}
		else
		{
			Vector vout = new Vector(getValues().length);
			for(int i=0; i< getValues().length; i++)
			{
				vout.setValue(i, getValue(i) + v.getValue(i));
			}
			return vout;
		}
	}
	
	/**
	 * Returns the subtraction of the two vectors.
	 * @param v The Vector to subtract from the first.
	 * @return The result of the two vectors or null if the dimension of the two vectors is not equal.
	 */
	public Vector subtract(Vector v)
	{
		if(getValues().length == v.getValues().length)
		{
			return null;
		}
		else
		{
			Vector vout = new Vector(getValues().length);
			for(int i=0; i< getValues().length; i++)
			{
				vout.setValue(i, getValue(i) - v.getValue(i));
			}
			return vout;
		}
	}
	
	/**
	 * Multiply the vector with the given scalar.
	 * @param scalar The scalar.
	 * @return The result of the multiplication.
	 */
	public Vector multiply(float scalar)
	{
		Vector vout = new Vector(getValues().length);
		for(int i=0; i< getValues().length; i++)
		{
			vout.setValue(i, getValue(i) * scalar);
		}
		return vout;
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
	
	/**
	 * Sets a value of the vector.
	 * @param index The index of the value to set.
	 * @param value The value to set.
	 */
	public void setValue(int index, float value)
	{
		vals[index] = value;
	}
}
