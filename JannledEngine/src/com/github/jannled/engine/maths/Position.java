package com.github.jannled.engine.maths;

import com.github.jannled.lib.math.Vector;

/**
 * A 3 dimensional position
 * @author Jannled
 */
public class Position extends Vector
{
	public Position()
	{
		super(3);
	}
	public Position(float x, float y, float z)
	{
		super(new float[] {x, y, z});
	}
	
	public float[] getPosition()
	{
		return getValues();
	}
	
	public float getX()
	{
		return getValues()[0];
	}
	
	public float getY()
	{
		return getValues()[1];
	}
	
	public float getZ()
	{
		return getValues()[2];
	}
}
