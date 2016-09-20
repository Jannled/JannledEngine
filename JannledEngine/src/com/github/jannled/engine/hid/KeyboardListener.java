package com.github.jannled.engine.hid;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.github.jannled.engine.Renderer;
import com.github.jannled.lib.Print;
import com.jogamp.opengl.awt.GLCanvas;

public class KeyboardListener
{
	GLCanvas canvas;
	
	public KeyboardListener(Renderer renderer)
	{
		this.canvas = renderer.getGLCanvas();
	}
	
	public void keyboardListener()
	{
		canvas.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e)
			{
				keyDown(e.getKeyLocation());
				Print.m("Key down");
			}
			
			@Override
			public void keyReleased(KeyEvent e)
			{
				Print.m("Key released");
			}
			
			@Override
			public void keyPressed(KeyEvent e)
			{
				Print.m("Key pressed");
			}
		});
	}
	
	public void keyDown(int key)
	{
		Print.m("Key " + key + " pressed!");
	}
}
