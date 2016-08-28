package com.github.jannled.engine.model;

import com.github.jannled.engine.maths.Vector3f;
import com.github.jannled.engine.scenegraph.SceneObject;

public class Model extends SceneObject
{
	private int vaoID;
	private Mesh mesh;
	private Vector3f position;
	
	public Model(int vaoID, Mesh mesh)
	{
		this.mesh = mesh;
		position = new Vector3f(0f, 0f, 0f);
		this.vaoID = vaoID;
	}
	
	public Model(int vaoID, String name, float[] positionData, float[] colorData, Material material, float[] textureCoordinates)
	{
		mesh = new Mesh(name, positionData, colorData, material, textureCoordinates);
		this.vaoID = vaoID;
	}
	
	public int getVAO()
	{
		return vaoID;
	}
	
	public Mesh getMesh()
	{
		return mesh;
	}
	
	public Vector3f getPosition()
	{
		return position;
	}
}
