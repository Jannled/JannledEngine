package com.github.jannled.engine;

import com.github.jannled.engine.scene.Scene;
import com.github.jannled.engine.scene.SceneObject;
import com.github.jannled.engine.shader.Shaderprogram;
import com.github.jannled.lib.Print;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import org.lwjgl.Version;
import org.lwjgl.opengl.GL;

/**
 * Class for handling resources like shaders and rendering the scene containing all the models and other objects
 * (or at least it's telling the resources to render themselves). 
 * @author Jannled
 */
public class Renderer
{
	private Scene activeScene;
	private Shaderprogram activePrograme;
	private Renderlooper renderlooper;
	
	/**
	 * Constructs a new renderer and prints out some debug information about the system. The renderer should be created after the 
	 * initialization phase is complete.
	 */
	public Renderer(Renderlooper renderlooper)
	{
		this.renderlooper = renderlooper;
		GL.createCapabilities();
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		
		Print.m(getDebugInfos());
		renderlooper.post(this);
	}

	/**
	 * Render a single frame, called by the window. This method loops through every object in the
	 * scene and tells them to render themselves. 
	 */
	public void renderFrame()
	{
		renderlooper.frame();
		
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		if(activeScene == null) return;
		
		for(SceneObject o : activeScene.getSceneObjects())
		{
			if(o.getVAO() > 0) o.render();
		}
		//glFlush();
	}
	
	/**
	 * Get the active scene.
	 * @return The currently active scene.
	 */
	public Scene getScene()
	{
		return activeScene;
	}
	
	public Shaderprogram getShaderPrograme()
	{
		return activePrograme;
	}
	
	/**
	 * Get some fancy system specifications.
	 * @return A list of some useful system informations.
	 */
	public static String getDebugInfos()
	{
		return "System specifications: " + 
				"\n	====================================================" + 
				"\n	 Operating System:	" + System.getProperty("os.name") + " " + System.getProperty("os.arch") +
				"\n	 Java Version:		" + System.getProperty("java.version") +
				"\n	 LWJGL Version:		" + Version.getVersion() + 
				"\n	 OpenGL Version:	" + glGetString(GL_VERSION) + 
				"\n	 Graphics Card:		" + glGetString(GL_RENDERER) + 
				"\n	====================================================";
	}
	
	/**
	 * Set the active scene.
	 * @param scene The scene the renderer should render next.
	 */
	public void setScene(Scene scene)
	{
		this.activeScene = scene;
	}
	
	public void setShaderPrograme(Shaderprogram shaderprograme)
	{
		this.activePrograme = shaderprograme;
		glUseProgram(activePrograme.getProgramID());
	}
}
