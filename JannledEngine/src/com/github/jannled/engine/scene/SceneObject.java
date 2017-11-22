package com.github.jannled.engine.scene;

import com.github.jannled.engine.shader.Shaderprogram;
import com.github.jannled.lib.Print;
import com.github.jannled.lib.math.Vector;

import static org.lwjgl.opengl.GL30.*;

public abstract class SceneObject
{
	protected Shaderprogram shaderprogram;
	protected int vaoID = -1;
	
	private Vector position;
	private Vector rotation;
	private Vector scale;
	
	public SceneObject(Vector position)
	{
		setPosition(position);
		setRotation(0, 0, 0);
		setScale(1, 1, 1);
	}
	
	public SceneObject(Vector position, Vector rotation, Vector scale)
	{
		setPosition(position);
		setRotation(rotation);
		setScale(scale);
	}
	
	/**
	 * Upload the data to the GPU. Generates the VAO id, binds it and then calls init().
	 * @param shaderprogram The shaderprogram to query for uniform variables.
	 */
	public void upload(Shaderprogram shaderprogram)
	{
		this.shaderprogram = shaderprogram;
		
		Thread t = Thread.currentThread();
		if(t.getId() != 1)
		{
			Print.e("Tried to upload " + toString() + " from Thread " + t.getName() + "(" + t.getId() + ") but it is not the main Thread!");
			return;
		}
		vaoID = glGenVertexArrays();
		glBindVertexArray(vaoID);
		Print.d("Uploading " + toString() + " to the GPU.");
		init();
		glBindVertexArray(0);
	}
	
	/**
	 * Upload the data to the GPU. The vao is already created and bound and gets unbound afterwards.
	 */
	protected abstract void init();
	
	/**
	 * Render this object, called every frame by the renderer. The model matrix is already recalculated and uploaded.
	 */
	public abstract void render();
	
	/**
	 * Get the Vertex Array handle of this object. 
	 * @return The VAO id or -1 if not yet allocated.
	 */
	public int getVAO()
	{
		return vaoID;
	}
	
	/**
	 * Get the current position of this object.
	 * @return The position in x, y and z coordinates.
	 */
	public Vector getPosition()
	{
		return position;
	}
	
	/**
	 * Get the current rotation of this object.
	 * @return The rotation in degrees.
	 */
	public Vector getRotation()
	{
		return rotation;
	}
	
	/**
	 * Get the current scale of this object.
	 * @return The scaling factors x, y and z.
	 */
	public Vector getScale()
	{
		return scale;
	}
	
	/**
	 * Set the position in 3D space.
	 * @param position The position in x, y and z coordinates.
	 */
	public void setPosition(Vector position)
	{
		this.position = position;
		if(position.getValues().length != 3)	Print.e("The " + toString() + " must have 3 position coordinates (x,y,z)!");
	}
	
	/**
	 * Set the position in 3D space.
	 * @param x The x coordinate of the object.
	 * @param y The y coordinate of the object.
	 * @param z The z coordinate of the object.
	 */
	public void setPosition(double x, double y, double z)
	{
		setPosition(new Vector(new double[] {x, y, z}));
	}
	
	/**
	 * Set the rotation of the object.
	 * @param position The rotation along the three axis.
	 */
	public void setRotation(Vector rotation)
	{
		this.rotation = rotation;
		if(rotation.getValues().length != 3)	Print.e("The " + toString() + " must have 3 rotation angles (x,y,z)!");
	}
	
	/**
	 * Set the rotation of the object.
	 * @param x The rotation along the x-axis.
	 * @param y The rotation along the y-axis.
	 * @param z The rotation along the z-axis.
	 */
	public void setRotation(double x, double y, double z)
	{
		setRotation(new Vector(new double[] {x, y, z}));
	}
	
	/**
	 * Set the scale of the object.
	 * @param position The x, y and z scaling factors.
	 */
	public void setScale(Vector scale)
	{
		this.scale = scale;
		if(scale.getValues().length != 3)	Print.e("The " + toString() + " must have 3 scaling factors (x,y,z)!");
	}
	
	/**
	 * Set the scale of the object.
	 * @param x The x scaling factor.
	 * @param y The y scaling factor.
	 * @param z The z scaling factor.
	 */
	public void setScale(double x, double y, double z)
	{
		setScale(new Vector(new double[] {x, y, z}));
	}
	
	@Override
	public String toString()
	{
		return "SceneObject(ID: " + vaoID + ")";
	}
}
