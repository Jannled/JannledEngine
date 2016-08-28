package com.github.jannled.engine.model;

public class ModelData
{
	private String name;
	
	private int numberOfVertices;
	private float[] vertexPosition;
	private float[] vertexColor;
	
	private Material material;
	private float[] texturePosition;
	
	public ModelData(String name, float[] vertexPosition, float[] vertexColor, Material material, float[] texturePosition)
	{
		this.vertexPosition = vertexPosition;
		this.vertexColor = vertexColor;
		this.material = material;
		this.texturePosition = texturePosition;
	}
	
	public ModelData(ModelData modelData)
	{
		this.vertexPosition = modelData.getPositionData();
		this.vertexColor = modelData.getColorData();
		this.material = modelData.getMaterial();
		this.texturePosition = modelData.getTexturePosition();
	}
	
	public String getName()
	{
		return name;
	}
	
	public float[] getPositionData()
	{
		return vertexPosition;
	}
	
	public float[] getColorData()
	{
		return vertexColor;
	}
	
	public float[] getTexturePosition()
	{
		return texturePosition;
	}
	
	public Material getMaterial()
	{
		return material;
	}
	
	public int getVerticeCount()
	{
		return numberOfVertices;
	}
}
