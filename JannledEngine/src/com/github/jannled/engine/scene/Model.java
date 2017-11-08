package com.github.jannled.engine.scene;

import com.github.jannled.engine.Renderer;
import com.github.jannled.engine.maths.Position;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;

public class Model extends SceneObject
{
	private int vaoID;
	private Mesh mesh;
	private Material material;

	public Model(Position pos, Mesh mesh, Material material)
	{
		super(pos);
		this.mesh = mesh;
		this.material = material;
		
		glBindVertexArray(vaoID);
		mesh.toGPU(vaoID);
		//TODO material.toGPU(vaoID);
		glBindVertexArray(0);
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

	@Override
	public void render(Renderer caller)
	{
		glBindVertexArray(getVAOID());
		glDrawArrays(GL_TRIANGLES, 0, getVerticeCount());
	}

	@Override
	public void toGPU(int vaoID)
	{
		mesh.toGPU(vaoID);
		//TODO material.toGPU(vaoID);
	}
}
