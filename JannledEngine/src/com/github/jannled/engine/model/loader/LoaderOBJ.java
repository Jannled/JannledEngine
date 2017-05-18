package com.github.jannled.engine.model.loader;

import java.io.File;

import com.github.jannled.engine.model.Material;
import com.github.jannled.engine.model.Model;
import com.github.jannled.lib.FileUtils;
import com.github.jannled.lib.Print;

/**
 * 
 * @author Jannled
 * @version 0.0.1
 */
public class LoaderOBJ extends ModelFileLoader
{
	public LoaderOBJ()
	{
		
	}

	@Override
	public Model loadModel(File file)
	{
		String[] content = FileUtils.readTextFile(file);
		
		String name = "";
		Material material = null;
		float[] verticeCoords = new float[content.length*3];
		float[] verticeNormals = new float[content.length*3];
		float[] textureCoords = new float[content.length*3];
		int verticeCountIndex = 0;
		int verticeNormalsIndex = 0;
		int textureCoordsIndex = 0;
		
		for(int i=1; i<content.length+1; i++)
		{
			String[] args = content[i].split(" ", 2);
			String key = args[0];
			String[] values = args[1].split("");
			
			//Line is commented out, ignore it
			if(content[i].contains("#")) continue;
			
			//Load Material
			if(key.equals("mtl"))
			{
				if(material != null) ModelFileLoader.loadError(file, i, "A material was already defined!");
				material = mtl(values);
			}
			
			//Load Object Name
			else if(key.equals("o"))
			{
				name = args[1];
			}
			
			//Load Vertices
			else if(key.equals("v"))
			{
				if(values.length != 3) ModelFileLoader.loadError(file, i, values.length + " coordinates are not supported!");
				try {verticeCoords[i] = vertexCoordinate(values); verticeCountIndex++;}
				catch(NumberFormatException e) {ModelFileLoader.loadError(file, i, "NumberFormatException!");}
			}
			
			//Load Vertice Textures
			else if(key.equals("vt"))
			{
				textureCoords[i] = textureCoordinate(values);
			}
			
			//Load Vertice Normals
			else if(key.equals("vn"))
			{
				verticeNormals[i] = vertexNormal(values);
			}
		}
		//TODO Rework the params of Model and pass in apropiate params
		return new Model(0, 0, name, verticeCoords, new short[256], new float[256], material, textureCoords);
	}
	
	private Material mtl(String args[])
	{
		return null;
	}
	
	private float[] vertexCoordinate(String args[])
	{
		return new float[] {Float.parseFloat(args[0]), Float.parseFloat(args[1]), Float.parseFloat(args[2])};
	}
	
	private float[] vertexNormal(String args[])
	{
		return null;
	}
	
	private float[] textureCoordinate(String args[])
	{
		return null;
	}
}
