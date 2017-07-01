package com.github.jannled.engine.scene;

import com.github.jannled.engine.loader.GPUUpload;
import static org.lwjgl.opengl.GL15.*;

public class Mesh implements GPUUpload
{
	private int vertexID;
	private int indicesID;
	private float[] vertices;
	private float[] faces;
	
	public Mesh(float[] vertices, float[] faces)
	{
		this.vertices = vertices;
		this.faces = faces;
	}

	@Override
	public void toGPU(int vaoID)
	{
		vertexID = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vertexID);
		glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
		
		indicesID = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indicesID);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, faces, GL_STATIC_DRAW);
	}
	
}
