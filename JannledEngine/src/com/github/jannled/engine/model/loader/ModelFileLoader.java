package com.github.jannled.engine.model.loader;

import java.io.File;

import com.github.jannled.engine.model.Model;
import com.github.jannled.lib.Print;

/**
 * Abstract class for loading a File to a model
 * @author Jannled
 *
 */
public abstract class ModelFileLoader
{
	public abstract Model loadModel(File file);
	
	static void loadError(File file, int line, String message)
	{
		Print.e("" + file.getPath() + ":" + line + " -> " + message);
	}
}
