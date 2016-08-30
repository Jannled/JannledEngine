package com.github.jannled.engine;

import com.github.jannled.engine.model.Model;
import com.github.jannled.engine.scenegraph.Scene;
import com.github.jannled.engine.scenegraph.SceneObject;
import com.github.jannled.lib.Print;
import com.jogamp.opengl.GL4;

public class Renderpipeline
{
	GL4 gl;
	
	public Renderpipeline(GL4 gl)
	{
		this.gl = gl;
	}
	
	public void renderFrame(Scene scene)
	{
		gl.glClear(GL4.GL_COLOR_BUFFER_BIT | GL4.GL_DEPTH_BUFFER_BIT);
		drawScene(scene);
		gl.glFlush();
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
				gl.glDrawElements(GL4.GL_TRIANGLES, model.getMesh().getIndices().length, GL4.GL_INT, 0);
			}
			else
			{
				Print.e("Found an unsupported Object in the SceneGraph!");
			}
		}
	}
}
