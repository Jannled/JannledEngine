package com.github.jannled.test;

import java.util.Arrays;

import com.github.jannled.engine.Window;
import com.github.jannled.lib.Print;

public class EngineTest
{
	Window window = new Window("JannledEngine", 1280, 720);
	
	public static void main(String[] args)
	{
		Print.setOutputLevel(Print.ALL);
		
		Print.d(Arrays.toString(args));
		new EngineTest();
	}
}
