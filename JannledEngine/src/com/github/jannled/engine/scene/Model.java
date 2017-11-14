package com.github.jannled.engine.scene;

import com.github.jannled.engine.Renderer;
import com.github.jannled.engine.maths.Position;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

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
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, mesh.getIndiceID());
		glDrawElements(GL_TRIANGLES, mesh.allFaces().length, GL_UNSIGNED_SHORT, 0);
	}

	@Override
	public void toGPU(int vaoID)
	{
		mesh.toGPU(vaoID);
		//TODO material.toGPU(vaoID);
	}
}
