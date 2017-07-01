package com.github.jannled.engine;

import com.github.jannled.engine.scene.Model;
import com.github.jannled.engine.scene.Scene;
import com.github.jannled.engine.scene.SceneObject;

public class Renderloop
{
	private Scene activeScene;
	
	public void renderFrame()
	{
		if(activeScene == null) return;
		
		for(SceneObject o : activeScene.getSceneObjects())
		{
			if(o instanceof Model)
			{
				((Model) o).getVAOID();
			}
		}
	}
	
	public Scene getScene()
	{
		return activeScene;
	}
	
	public void setScene(Scene scene)
	{
		this.activeScene = scene;
	}
}
