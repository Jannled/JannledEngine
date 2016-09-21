package com.github.jannled.engine.gui;

import com.jogamp.opengl.awt.GLCanvas;

public class GUIManager
{
	GLCanvas canvas;
	boolean showFPS = true;
	
	public GUIManager(GLCanvas canvas)
	{
		this.canvas = canvas;
	}
	
	public void draw()
	{
		if(showFPS) canvas.getGraphics().drawString((int)canvas.getAnimator().getLastFPS() + "FPS", canvas.getWidth()-40, 10);
	}
	
	public void showFPS(boolean fps)
	{
		this.showFPS = fps;
	}
}
