package com.github.jannled.engine.maths;

import java.nio.FloatBuffer;

import com.jogamp.common.nio.Buffers;

/**
 * Matrix utils class
 * @author Jannled, TheChernoProject
 * @version 0.0.2
 */
public class Matrix4f 
{
	public static final int SIZE = 4 * 4;
	//[Zeile + Spalte*4]
	public float[] elements = new float[SIZE];
	
	public Matrix4f()
	{
		for(int i=0; i < SIZE; i++)
			this.elements[i] = 0.0F;
	}
	
	/**
	 * Returns the <b>Identity Matrix</b>.
	 * @return The Identity Matrix
	 */
	public static Matrix4f identity()
	{
		Matrix4f result = new Matrix4f();
		
		result.elements[0 + 0 * 4] = 1.0f;
		result.elements[1 + 1 * 4] = 1.0f;
		result.elements[2 + 2 * 4] = 1.0f;
		result.elements[3 + 3 * 4] = 1.0f;
		
		return result;
	}
	
	/**
	 * A Matrix needed for ortographic projection.
	 * @param left Left
	 * @param right Right
	 * @param bottom Bottom
	 * @param top Top
	 * @param near Near Clipping Plane
	 * @param far Far Clipping Plane
	 * @return A Matrix representing the ortographic projection
	 */
	public static Matrix4f orthographic(float left, float right, float bottom, float top, float near, float far)
	{
		Matrix4f result = identity();
		
		result.elements[0 + 0 * 4] = 2.0f / (right-left);
		result.elements[1 + 1 * 4] = 2.0f / (right-left);
		result.elements[2 + 2 * 4] = 2.0f / (right-left);
		
		result.elements[0 + 3 * 4] = (left+right) / (left-right);
		result.elements[1 + 3 * 4] = (bottom+top) / (bottom-top);
		result.elements[2 + 3 * 4] = (far+near) / (far-near);
		return result;
	}
	
	/**
	 * A Matrix needed for perspective projection.
	 * @param left Left
	 * @param right Right
	 * @param bottom Bottom
	 * @param top Top
	 * @param near Near Clipping Plane
	 * @param far Far Clipping Plane
	 * @return A Matrix representing the perspective projection
	 */
	public static Matrix4f perspective(float left, float right, float bottom, float top, float near, float far)
	{
		Matrix4f result = new Matrix4f();
		
		result.elements[0 + 0 * 4] = (2*near) / (right-left);
		result.elements[1 + 1 * 4] = (2*near) / (top-bottom);
		result.elements[0 + 2 * 4] = (right+left) / (right-left);
		result.elements[1 + 2 * 4] = (top+bottom) / (top-bottom);
		result.elements[2 + 2 * 4] = -(far+near) / (far-near);
		result.elements[3 + 2 * 4] = -1.0F;
		result.elements[2 + 3 * 4] = (2*far*near) / (far-near);
		
		return result;
	}
	
	public Matrix4f multiply(Matrix4f matrix)
	{
		Matrix4f result = new Matrix4f();
		
		for(int y=0; y < 4; y++) {
			for(int x=0; x < 4; x++) {
				float sum = 0.0f;
				for(int e=0; e < 4; e++) {
					sum += this.elements[e + y * 4] * matrix.elements[x + e * 4];
				}
				result.elements[x + y * 4] = sum;
			}
		}
		return result;
	}
	
	/**
	 * Translates the Matrix by the given Offset.
	 * @param vector Offset of the translation
	 * @return The translated Matrix
	 */
	public static Matrix4f translate(Vector3f vector)
	{
		Matrix4f result = identity();
		result.elements[0 + 3 * 4] = vector.x;
		result.elements[1 + 3 * 4] = vector.y;
		result.elements[2 + 3 * 4] = vector.z;
		
		return result;
	}
	
	/**
	 * Rotates the Matrix by the given Degree and axis.
	 * @param angle Angle in degees
	 * @param x <b>1</b> if the matrix should be rotated along the x axis, <b>0</b> otherwise.
	 * @param y <b>1</b> if the matrix should be rotated along the y axis, <b>0</b> otherwise.
	 * @param z <b>1</b> if the matrix should be rotated along the z axis, <b>0</b> otherwise.
	 * @return
	 */
	public static Matrix4f rotate(float angle, float x, float y, float z)
	{
		Matrix4f result = identity();
		float r = (float) Math.toRadians(angle);
		float cos = (float) Math.cos(r);
		float sin = (float) Math.sin(r);
		float omc = 1.0f - cos;
		
		result.elements[0 + 0 * 4] = x * omc + cos;
		result.elements[0 + 0 * 4] = y * x * omc + z * sin;
		result.elements[0 + 0 * 4] = x * z * omc - y * sin;
		
		result.elements[0 + 0 * 4] = x * y * omc - z * sin;
		result.elements[0 + 0 * 4] = y * omc + cos;
		result.elements[0 + 0 * 4] = y * z * omc + x * sin;
		
		result.elements[0 + 0 * 4] = x * z * omc + y * sin;
		result.elements[0 + 0 * 4] = y * z * omc - x * sin;
		result.elements[0 + 0 * 4] = z * omc + cos;
		
		return result;
	}
	
	/**
	 * 
	 * @return A Floatbuffer with the matrix in it.
	 */
	public FloatBuffer toFloatBuffer()
	{
		return Buffers.newDirectFloatBuffer(elements);
	}
}
