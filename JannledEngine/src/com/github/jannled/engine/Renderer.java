package com.github.jannled.engine;

import com.github.jannled.engine.scene.Scene;
import com.github.jannled.engine.scene.SceneObject;
import com.github.jannled.lib.Print;

import static org.lwjgl.opengl.GL11.*;

/**
 * Class for handling resources like shaders and rendering the scene containing all the models and other objects
 * (or at least it's telling the resources to render themselves).
 * @author Jannled
 */
public class Renderer
{
	private Scene activeScene;
	
	/**
	 * Constructs a new renderer and prints out some debug information about the system.
	 */
	public Renderer()
	{
		Print.m(getDebugInfos());
	}
	
	/**
	 * Render a single frame, called by the window. This method loops through every object in the
	 * scene and tells them to render themselves. 
	 */
	public void renderFrame()
	{
		if(activeScene == null) return;
		
		for(SceneObject o : activeScene.getSceneObjects())
		{
			o.render(this);
		}
	}
	
	/**
	 * Get the active scene.
	 * @return The currently active scene.
	 */
	public Scene getScene()
	{
		return activeScene;
	}
	
	/**
	 * Set the active scene.
	 * @param scene The scene the renderer should render next.
	 */
	public void setScene(Scene scene)
	{
		this.activeScene = scene;
	}
	
	/**
	 * Get some fancy system specifications.
	 * @return A list of some useful system informations.
	 */
	public static String getDebugInfos()
	{
		return "System properties: " + 
				"\n	====================================================" + 
				"\n	 Operating System:	" + System.getProperty("os.name") + " " + System.getProperty("os.arch") +
				"\n	 Java Version:		" + System.getProperty("java.version") +
				"\n	 OpenGL Version:	" + glGetString(GL_VERSION) + 
				"\n	 Graphics Card:		" + glGetString(GL_RENDERER) + 
				"\n	====================================================";
	}
}
