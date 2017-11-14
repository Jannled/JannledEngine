package com.github.jannled.engine.scene;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import java.nio.FloatBuffer;

import com.github.jannled.engine.loader.GPUUpload;
import com.github.jannled.engine.utils.Utils4Buffers;

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
		vertexID = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vertexID);
		glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
		
		int vao = glGenVertexArrays();
		glBindVertexArray(vao);
		glEnableVertexAttribArray(0);
		glBindBuffer(GL_ARRAY_BUFFER, vertexID);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, Utils4Buffers.toFloatBufffer(vertices));
	}
	
	public short[] allFaces()
	{
		return indices;
	}
	
	public int getIndiceID()
	{
		return indicesID;
	}
	
	public float[] getVertices()
	{
		return vertices;
	}
}
