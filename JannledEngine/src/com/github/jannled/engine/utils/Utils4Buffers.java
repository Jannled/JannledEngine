package com.github.jannled.engine.utils;

import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

public class Utils4Buffers
{
	public static FloatBuffer toFloatBufffer(float[] values)
	{
		FloatBuffer out = BufferUtils.createFloatBuffer(values.length);
		out.put(values);
		out.flip();
		return out;
	}
	
	public static FloatBuffer toFloatBufffer(double[] values)
	{
		FloatBuffer out = BufferUtils.createFloatBuffer(values.length);
		for(int i=0; i<values.length; i++)
		{
			out.put((float) values[i]);
		}
		out.flip();
		return out;
	}
	
	public static DoubleBuffer toDoubleBufffer(double[] values)
	{
		DoubleBuffer out = BufferUtils.createDoubleBuffer(values.length);
		out.put(values);
		out.flip();
		return out;
	}
}
