package com.github.jannled.engine.model;

import com.github.jannled.engine.maths.Vector3f;
import com.github.jannled.engine.scenegraph.SceneObject;

public class Model extends SceneObject
{
	private int vaoID;
	private int indiceID;
	private Mesh mesh;
	private Vector3f position;
	
	public Model(int vaoID, int indiceID, Mesh mesh)
	{
		this.mesh = mesh;
		position = new Vector3f(0f, 0f, 0f);
		this.vaoID = vaoID;
		this.indiceID = indiceID;
	}
	
	public Model(int vaoID, int indiceID, String name, float[] positionData, int[] indicesData, float[] colorData, Material material, float[] textureCoordinates)
	{
		mesh = new Mesh(name, positionData, indicesData, colorData, material, textureCoordinates);
		this.vaoID = vaoID;
		this.indiceID = indiceID;
	}
	
	public int getVAO()
	{
		return vaoID;
	}
	
	public int getIndiceID()
	{
		return indiceID;
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
