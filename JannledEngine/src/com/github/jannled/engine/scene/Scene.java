package com.github.jannled.engine.scene;

import java.util.LinkedList;

public class Scene
{
	private LinkedList<SceneObject> objects;
	
	public Scene()
	{
		objects = new LinkedList<SceneObject>();
	}
	
	public void addSceneObject(SceneObject so)
	{
		objects.add(so);
	}
	
	public LinkedList<SceneObject> getSceneObjects()
	{
		return objects;
	}
}
