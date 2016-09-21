package com.github.jannled.engine.hid;

import java.awt.AWTException;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import com.github.jannled.engine.maths.Vector2f;
import com.github.jannled.lib.Print;

/**
 * This class requests the mouse events and sends it to InputHandler
 * @author Jannled
 * @version 0.0.1
 * @see com.github.jannled.engine.hid
 */
public class MouseHandler implements MouseListener, MouseMotionListener
{
	InputHandler inputHandler;
	
	boolean isWindowFocused;
	boolean mouseLock;
	
	BufferedImage blankCursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
	Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(blankCursorImg, new Point(0, 0), "blank cursor");
	
	public MouseHandler(InputHandler inputHandler)
	{
		this.inputHandler = inputHandler;
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		inputHandler.mousePressed(e.getButton());
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		isWindowFocused = true;
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		isWindowFocused = false;
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
		
	}

	@Override
	public void mouseMoved(MouseEvent event)
	{
		if(mouseLock == true)
		{
			Point cursorPos = event.getLocationOnScreen();
			Point windowPos = inputHandler.getComponent().getLocationOnScreen();
			Dimension windowSize = inputHandler.getComponent().getSize();
			Point windowMiddle = new Point((int)(windowPos.getX() + windowSize.getWidth()/2), (int)(windowPos.getY() + windowSize.getHeight()/2));
			Vector2f devitation = new Vector2f((float)(cursorPos.getX() - windowMiddle.getX()), (float)(windowMiddle.getY() - cursorPos.getY()));
			
			Robot robot = null;
			try
			{
				robot = new Robot();
			} catch (AWTException e)
			{
				Print.e("Fatal error: Unable to create Java Robot to center mouse on screen!");
				System.exit(-1);
			}
			inputHandler.mouseLock(devitation);
			robot.mouseMove((int)windowMiddle.getX(), (int)windowMiddle.getY());
		}
	}
	
	public boolean isWindowFocused()
	{
		return isWindowFocused;
	}
	
	/**
	 * Locks the mouse to the window center
	 * @param locked If the mouse should be locked
	 */
	public void lockMouse(boolean locked)
	{
		this.mouseLock = locked;
		if(locked)
		{
			inputHandler.getComponent().setCursor(blankCursor);
		}
		else
		{
			inputHandler.getComponent().setCursor(Cursor.getDefaultCursor());
		}
	}
	
	public boolean isMouseLocked()
	{
		return mouseLock;
	}
}
