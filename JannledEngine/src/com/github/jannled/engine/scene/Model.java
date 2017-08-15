package com.github.jannled.engine.scene;

import com.github.jannled.engine.loader.GPUUpload;
import com.github.jannled.engine.maths.Position;

import static org.lwjgl.opengl.GL30.*;

public class Model extends SceneObject implements GPUUpload
{
	private int vaoID;
	private Mesh mesh;
	private Material material;

	public Model(Position pos, Mesh mesh, Material material)
	{
		super(pos);
		this.mesh = mesh;
		this.material = material;
		
		toGPU(vaoID);
		
		glBindVertexArray(vaoID);
		mesh.toGPU(vaoID);
		//TODO material.toGPU(vaoID);
		glBindVertexArray(0);
	}

	@Override
	public void toGPU(int vaoID)
	{
		vaoID = glGenVertexArrays();
	}
	
	public int getVAOID()
	{
		return vaoID;
	}
		
	public Mesh getMesh()
	{
		return mesh;
	}

	public Material getMaterial()
	{
		return material;
	}

	public int getVerticeCount()
	{
		return mesh.getVertices().length;
	}
}
