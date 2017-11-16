package com.github.jannled.engine.scene;

import com.github.jannled.lib.Print;
import com.github.jannled.lib.math.Vector;

import static org.lwjgl.opengl.GL30.*;

public abstract class SceneObject
{
	protected int vaoID = -1;
	protected Vector position;
	
	/**
	 * Upload the data to the GPU. Generates the VAO id, binds it and then calls init().
	 */
	public void upload()
	{
		Thread t = Thread.currentThread();
		if(t.getId() != 1)
		{
			Print.e("Tried to upload the model with VAO id " + vaoID + " from Thread " + t.getName() + "(" + t.getId() + ") but it is not the main Thread!");
			return;
		}
		vaoID = glGenVertexArrays();
		glBindVertexArray(vaoID);
		Print.d("Uploading model with VAO id " + vaoID + " to the GPU.");
		init();
		glBindVertexArray(0);
	}
	
	/**
	 * Upload the data to the GPU. The vao is already created and bound and gets unbound afterwards.
	 */
	protected abstract void init();
	
	/**
	 * Render this object, called every frame by the renderer.
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
	
	@Override
	public String toString()
	{
		return "VAO id: " + vaoID;
	}
}
