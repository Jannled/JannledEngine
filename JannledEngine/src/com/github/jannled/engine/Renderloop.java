package com.github.jannled.engine;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

import com.github.jannled.engine.scene.Model;
import com.github.jannled.engine.scene.Scene;
import com.github.jannled.engine.scene.SceneObject;

public class Renderloop implements Runnable
{
	private Scene activeScene;
	private long window;
	
	public Renderloop(long window)
	{
		this.window = window;
	}
	
	@Override
	public void run()
	{
		while(!glfwWindowShouldClose(window))
		{
			glfwPollEvents();
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			renderFrame();
			glfwSwapBuffers(window);
		}
	}
	
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
