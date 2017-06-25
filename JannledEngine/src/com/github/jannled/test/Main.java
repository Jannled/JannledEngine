package com.github.jannled.test;

import com.github.jannled.engine.Window;

public class Main
{
	Window window;
	
	private Main()
	{
		window = new Window("Test Version of the JannledEngine", 1280, 720);
	}
	
	public static void main(String[] args)
	{
		new Main();
	}

}
