package com.github.jannled.engine.scene;

import java.util.LinkedList;

import com.github.jannled.lib.Print;

/**
 * Acts as a que to create the objects and upload them to the GPU when the Renderer Thread which holds the OpenGL instance
 * is ready to call them.
 * @author Jannled
 */
public class GPUUploader
{
	/** The instance of the GPU uploader.*/
	public static GPUUploader gup;
	
	volatile LinkedList<SceneObject> que = new LinkedList<SceneObject>();
	
	public GPUUploader()
	{
		if(gup!=null) Print.e("There can only be one GPU uploader!");
		GPUUploader.gup = this;
	}
	
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
