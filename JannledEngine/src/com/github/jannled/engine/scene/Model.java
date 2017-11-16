package com.github.jannled.engine.scene;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import com.github.jannled.lib.math.Vector;

public class Model extends SceneObject
{
	private float[] vertices;
	private int verticesID;
	
	public Model(Vector position, float[] vertices)
	{
		super(position);
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
		glBindVertexArray(getVAO());
		glDrawArrays(GL_TRIANGLES, 0, vertices.length);
	}
	
	public float[] getVertices()
	{
		return vertices;
	}
}
