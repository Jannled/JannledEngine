package com.github.jannled.engine.loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import com.github.jannled.engine.scene.Model;

/**
 * Class to load OBJ-files into memory
 * @author Jannled
 * @version 0.0.1
 */
public class OBJLoader
{
	private static String COMMENT = "#";
	private static String VERTEX = "v";
	private static String TEXTURE = "vt";
	private static String NORMAL = "vn";
	private static String NAME = "o";
	
	public static Model loadModel(InputStream in)
	{
		try
		{
			BufferedReader r = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
			String[] line;
			while((line = r.readLine().split(" ")) != null) 
			if(line[0].equals(COMMENT))
			{
				
			}
			r.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private static void loadVertice()
	{
		
	}
}