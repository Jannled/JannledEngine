package com.github.jannled.engine.model;

public class Mesh
{
	private String name;
	
	private int numberOfVertices;
	private float[] vertexPosition;
	private int[] indices;
	private float[] vertexColor;
	
	private Material material;
	private float[] texturePosition;
	
	public Mesh(String name, float[] vertexPosition, int[] indices, float[] vertexColor, Material material, float[] texturePosition)
	{
		this.vertexPosition = vertexPosition;
		this.indices = indices;
		this.vertexColor = vertexColor;
		this.material = material;
		this.texturePosition = texturePosition;
		this.numberOfVertices = vertexPosition.length;
	}
	
	public Mesh(Mesh mesh)
	{
		this.vertexPosition = mesh.getPositionData();
		this.vertexColor = mesh.getColorData();
		this.material = mesh.getMaterial();
		this.texturePosition = mesh.getTexturePosition();
	}
	
	public String getName()
	{
		return name;
	}
	
	public float[] getPositionData()
	{
		return vertexPosition;
	}
	
	public int[] getIndices()
	{
		return indices;
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
