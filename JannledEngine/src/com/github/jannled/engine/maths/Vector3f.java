package com.github.jannled.engine.maths;

/**
 * 
 * @author Jannled, TheChernoProject
 *
 */
public class Vector3f 
{
	float x;
	float y;
	float z;
	
	public Vector3f()
	{
		x = 0.0f;
		y = 0.0f;
		z = 0.0f;
	}
	public Vector3f(float x, float y, float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public float[] getVector()
	{
		return new float[] {x, y, z};
	}
	
	public float getX()
	{
		return x;
	}
	
	public float getY()
	{
		return y;
	}
	
	public float getZ()
	{
		return z;
	}
}
