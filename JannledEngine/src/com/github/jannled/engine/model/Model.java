package com.github.jannled.engine.model;

public class Model extends ModelData
{
	private int vaoID;
	
	public Model(int vaoID, ModelData modelData)
	{
		super(modelData);
		this.vaoID = vaoID;
	}
	
	public Model(int vaoID, String name, float[] positionData, float[] colorData, Material material, float[] textureCoordinates)
	{
		super(name, positionData, colorData, material, textureCoordinates);
		this.vaoID = vaoID;
	}
	
	public int getVAO()
	{
		return vaoID;
	}
}
