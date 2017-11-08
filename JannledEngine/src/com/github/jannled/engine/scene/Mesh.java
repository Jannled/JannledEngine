package com.github.jannled.engine.scene;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

import com.github.jannled.engine.loader.GPUUpload;

public class Mesh implements GPUUpload
{
	private int vertexID;
	private int normalID;
	private int uvID;
	private int indicesID;
	
	private float[] vertices;
	private float[] normals;
	private float[] uvs;
	private short[] indices;
	
	public Mesh(float[] vertices, float[] normals, float[] uvs, short[] indices)
	{
		this.vertices = vertices;
		this.normals = normals;
		this.uvs = uvs;
		this.indices = indices;
	}

	@Override
	public void toGPU(int vaoID)
	{
		//generate VBOs
		vertexID = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vertexID);
		glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
		
		glEnableVertexAttribArray(0);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
		
		normalID = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, normalID);
		glBufferData(GL_ARRAY_BUFFER, normals, GL_STATIC_DRAW);
		
		glEnableVertexAttribArray(1);
		glVertexAttribPointer(1, 3, GL_FLOAT, false, 0, 0);
		
		uvID = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, uvID);
		glBufferData(GL_ARRAY_BUFFER, uvs, GL_STATIC_DRAW);
		
		glEnableVertexAttribArray(2);
		glVertexAttribPointer(2, 2, GL_FLOAT, false, 0, 0);
		
		indicesID = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indicesID);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, allFaces(), GL_STATIC_DRAW);
	}
	
	private short[] allFaces()
	{
		return indices;
	}
	
	public float[] getVertices()
	{
		return vertices;
	}
}
