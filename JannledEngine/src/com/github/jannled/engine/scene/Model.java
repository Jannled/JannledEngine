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
	
	private int[] faces;
	private int indicesID;
	
	public Model(Vector position, float[] vertices, int[] faces)
	{
		super(position);
		this.vertices = vertices;
		this.faces = faces;
	}
	
	@Override
	protected void init()
	{
		verticesID = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, verticesID);
		glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
		
		glEnableVertexAttribArray(0);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
		
		indicesID = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indicesID);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, faces, GL_STATIC_DRAW);
	}

	@Override
	public void render()
	{
		glBindVertexArray(getVAO());
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indicesID);
		glDrawElements(GL_TRIANGLES, faces.length, GL_UNSIGNED_INT, 0);
	}
	
	public float[] getVertices()
	{
		return vertices;
	}
	
	@Override
	public String toString()
	{
		return "Model(ID: " + vaoID + ")";
	}
}
