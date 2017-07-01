package com.github.jannled.engine.loader;

import java.io.InputStream;

import com.github.jannled.engine.scene.Model;

public interface ModelLoader
{
	public abstract Model load(InputStream file);
}
