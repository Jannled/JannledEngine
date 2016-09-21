package com.github.jannled.engine.hid;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardHandler implements KeyListener
{
	InputHandler inputHandler;
	
	public KeyboardHandler(InputHandler inputHandler)
	{
		this.inputHandler = inputHandler;
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		inputHandler.keyPressed(e.getKeyCode());
	}
	
	@Override
	public void keyTyped(KeyEvent e)
	{
		
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		
	}
}
