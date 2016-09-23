package com.github.jannled.engine;

import com.github.jannled.engine.gui.GUIManager;
import com.github.jannled.engine.model.Model;
import com.github.jannled.engine.scenegraph.Scene;
import com.github.jannled.engine.scenegraph.SceneObject;
import com.github.jannled.engine.shaders.ShaderLoader;
import com.github.jannled.lib.Print;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.awt.GLCanvas;

public class Renderpipeline
{
	GL4 gl;
	GLCanvas canvas;
	GUIManager guiManager;
	ShaderLoader shaderLoader;
	
	public Renderpipeline(Renderer renderer)
	{
		this.gl = renderer.getGL();
		this.canvas = renderer.getGLCanvas();
		this.guiManager = new GUIManager(canvas);
		this.shaderLoader = renderer.getShaderLoader();
	}
	
	public void renderFrame(Scene scene)
	{
		gl.glClear(GL4.GL_COLOR_BUFFER_BIT | GL4.GL_DEPTH_BUFFER_BIT);
		drawScene(scene);
		gl.glFlush();
		guiManager.draw();
	}
	
	public void drawScene(Scene scene)
	{
		for(SceneObject sceneObject : scene.getObjects())
		{
			if(sceneObject instanceof Model)
			{
				Model model = (Model) sceneObject;
				gl.glBindVertexArray(model.getVAO());
				gl.glBindBuffer(GL4.GL_ELEMENT_ARRAY_BUFFER, model.getIndiceID());
				gl.glDrawElements(GL4.GL_TRIANGLES, model.getMesh().getIndices().length, GL4.GL_UNSIGNED_SHORT, 0);
			}
			else
			{
				Print.e("Found an unsupported Object in the SceneGraph!");
			}
		}
	}
}
