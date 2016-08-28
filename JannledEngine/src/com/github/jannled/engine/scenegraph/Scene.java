package com.github.jannled.engine.scenegraph;

import java.util.Vector;

public class Scene
{
	private String name;
	Vector<SceneObject> scene = new Vector<SceneObject>();
	
	public Scene(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void addToScene(SceneObject sceneObject)
	{
		scene.add(sceneObject);
	}
	
	public Vector<SceneObject> getObjects()
	{
		return scene;
	}
}
