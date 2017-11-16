package com.github.jannled.engine.scene;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

public class Model extends SceneObject
{
	private float[] vertices;
	private int verticesID;
	
	public Model(float[] vertices)
	{
		this.vertices = vertices;
	}
	
	@Override
	protected void init()
	{
		verticesID = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, verticesID);
		glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
		
		glEnableVertexAttribArray(0);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
	}

	@Override
	public void render()
	{
		glDrawArrays(GL_TRIANGLES, 0, vertices.length);
	}
	
	public float[] getVertices()
	{
		return vertices;
	}
}
