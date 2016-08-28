package com.github.jannled.engine.model.loader;

import java.io.File;

import com.github.jannled.lib.FileUtils;
import com.github.jannled.lib.Print;

public class ModelFileLoader
{
	public ModelFileLoader()
	{
		
	}
	
	/**
	 * Reads the text-file containing the model information to a String-Array
	 * @param file The file to read.
	 * @return A Stringarray containing each of the lines in one array-entry
	 */
	public String[] loadFile(File file)
	{
		String[] content = FileUtils.readTextFile(file);
		return content;
	}
	
	/**
	 * Searches if the line begins with the specified lookup, like so: <br>
	 * v 1.0 1.0 1.0  | then lookup will be v
	 * @param text The text to analyse for the lookup.
	 * @param lookup A single charackter. Here are some examples for .obj <table> <tr> <td>o</td> <td>Object name</td> </tr> <tr> <td>v</td> <td>Vertex Position</td> </table>
	 * @return The three floats for each line beginning with the lookup.
	 */
	public float[] getVertexData(String[] text, String lookup)
	{
		int numLines = text.length;
		float[] output = new float[numLines];
		
		int index = 0;
		for(int i=0; i<numLines; i++)
		{
			String line = text[i];
			
			//Check if line begins with lookup 
			if(line.substring(0, line.toCharArray().length).equals(lookup + " "))
			{
				String[] data = line.split(" ");
				float x = 0.0F;
				float y = 0.0F;
				float z = 0.0F;
				
				//Check if line contains 3 Vertice Data
				if(data.length != 4)
					Print.e("Unsupported number of elements in this line " + (data.length-1) + "/3!");
				try 
				{
					x = Float.parseFloat(data[1]);
					y = Float.parseFloat(data[2]);
					z = Float.parseFloat(data[3]);
				}
				//Check if element is a valid number
				catch(NumberFormatException e) 
				{
					Print.e("Unnsoported " + lookup + " float in line " + i + "!");
				}
				//Append the vertices to the output
				output[index] = x;
				output[index+1] = y;
				output[index+2] = z;
				index = index+3;
			}
		}
		//Strip the array to the actual size
		float[] buffer = output;
		output = new float[index+1];
		for(int i=0; i<index+1; i++)
		{
			output[i] = buffer[i];
		}
			
		return output;
	}
	
	/**
	 * Searches if the line begins with the specified lookup, like so: <br>
	 * v 1.0 1.0 1.0  | then lookup will be v
	 * @param text The text to analyse for the lookup.
	 * @param lookup A single charackter. Here are some examples for .obj <table> <tr> <td>o</td> <td>Object name</td> </tr> <tr> <td>v</td> <td>Vertex Position</td> </table>
	 * @return The three floats for each line beginning with the lookup.
	 */
	public String[] getData(String[] text, String lookup)
	{
		int numLines = text.length;
		String[] output = new String[numLines];
		
		int index = 0;
		for(int i=0; i<numLines; i++)
		{
			String line = text[i];
			
			//Check if line begins with lookup
			if(line.substring(0, line.toCharArray().length).equals(lookup + " "))
			{
				String[] data = line.split(" ");
				String s = "<?>";
				
				//Check if line contains 1 String Data
				if(data.length != 2)
					Print.e("Unsupported number of elements in this line " + (data.length-1) + "/1!");
				
				//Append the vertices to the output
				output[index] = s;
				index = index+1;
			}
		}
		//Strip the array to the actual size
		String[] buffer = output;
		output = new String[index+1];
		for(int i=0; i<index+1; i++)
		{
			output[i] = buffer[i];
		}
		return output;
	}
}
