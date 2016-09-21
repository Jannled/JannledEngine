package com.github.jannled.engine.maths;

public class Vector2f
{
	private float x, y;
	
	public Vector2f()
	{
		x = 0.0f;
		y = 0.0f;
	}
	
	public Vector2f(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	public float[] getVector()
	{
		return new float[] {x, y};
	}
	
	public float getX()
	{
		return x;
	}
	
	public float getY()
	{
		return y;
	}
}
