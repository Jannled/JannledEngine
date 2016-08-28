package com.github.jannled.engine;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.github.jannled.lib.ArrayUtils;
import com.github.jannled.lib.Print;

/**
 * Main class for Jannleds Renderer Engine. Handles the start 
 * of the program and the window where the GLCanvas is added.
 * 
 * @author Jannled
 * @version 0.0.1
 */
public class Main 
{
	Renderer renderer = new Renderer(1280, 720, 60);
	
	JFrame window = new JFrame("Jannled Engine");
	
	public Main()
	{
		setupWindow();
		setupCloseEvent();
	}
	
	public void setupWindow()
	{
		window.getContentPane().add(renderer.getGLCanvas());
		window.pack();
		window.setVisible(true);
	}
	
	public void setupCloseEvent()
	{
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.addWindowListener( new WindowAdapter() 
		{
			public void windowClosing( WindowEvent windowevent ) 
			{
				Print.m("Cleaning Window.");
				window.removeAll();
				window.dispose();
			}
		});
	}

	public static void main(String[] args) 
	{
		Print.m("Starting JannledEngine with following parameters: " + ArrayUtils.arrayToString(args) + ".");
		new Main();
	}
}
