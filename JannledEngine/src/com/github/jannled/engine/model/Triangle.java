package com.github.jannled.engine.shapes;

public class Triangle extends Model
{
	public Triangle()
	{
		super(0, new float[1], new float[1]);
	}
	
	private float[] positionData = {
			-0.8f, -0.8f, 0.0f,
			 0.8f, -0.8f, 0.0f,
			 0.8f,  0.8f, 0.0f
	};
	
	private float[] colorData = {
			1.0f, 0.0f, 0.0f,
			0.0f, 1.0f, 0.0f,
			0.0f, 0.0f, 1.0f
	};
	
	@Override
	public float[] getPositionData()
	{
		return positionData;
	}

	@Override
	public float[] getColorData()
	{
		return colorData;
	}
	
}
