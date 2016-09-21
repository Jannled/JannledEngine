package com.github.jannled.engine.hid;

import java.awt.Component;
import java.awt.event.KeyEvent;

import com.github.jannled.engine.maths.Vector2f;

public class InputHandler
{
	KeyboardHandler keyboardListener;
	MouseHandler mouseListener;
	Component component;
	
	public InputHandler(Component component)
	{
		keyboardListener = new KeyboardHandler(this);
		mouseListener = new MouseHandler(this);
		this.component = component;
		
		component.addKeyListener(keyboardListener);
		component.addMouseListener(mouseListener);
		component.addMouseMotionListener(mouseListener);
	}
	
	public void keyPressed(int key)
	{
		if(key == KeyEvent.VK_ESCAPE) mouseListener.lockMouse(!mouseListener.isMouseLocked());
	}
	
	public void mousePressed(int key)
	{
		
	}
	
	public void mouseLock(Vector2f devitation)
	{
		
	}
	
	public Component getComponent()
	{
		return component;
	}
}
