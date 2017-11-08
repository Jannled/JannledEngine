package com.github.jannled.engine.scene;

import java.util.LinkedList;

/**
 * Acts as a que to create the objects and upload them to the GPU when the Renderer Thread which holds the OpenGL instance
 * is ready to call them.
 * @author Jannled
 */
public class GPUUploader
{
	volatile LinkedList<SceneObject> que = new LinkedList<SceneObject>();
	
	public void uploadSceneObjects()
	{
		while(!que.isEmpty())
		{
			que.remove().createGLObject();
		}
	}
	
	public void addToQue(SceneObject sceneObject)
	{
		que.add(sceneObject);
	}
}
