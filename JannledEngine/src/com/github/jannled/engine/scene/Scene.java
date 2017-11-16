package com.github.jannled.engine.scene;

import java.util.LinkedList;

/**
 * A scene containing all the assets, like lamps, models etc...
 * @author Jannled
 */
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