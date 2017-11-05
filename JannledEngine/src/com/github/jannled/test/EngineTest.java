package com.github.jannled.test;

import org.lwjgl.glfw.*;

import java.io.File;
import java.util.Arrays;

import com.github.jannled.engine.Window;
import com.github.jannled.engine.shader.Shader;
import com.github.jannled.engine.shader.Shaderprogram;
import com.github.jannled.lib.Print;

import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.opengl.GL20.*;

public class EngineTest implements Runnable
{
	Window window;
	
	public EngineTest()
	{
		Thread t = new Thread(this, "Sidestep");
		t.start();
		
		window = new Window("JannledEngine", 1280, 720);
	}
	
	public static void main(String[] args)
	{
		Print.setOutputLevel(Print.ALL);
		Print.d(Arrays.toString(args));
		new EngineTest();
	}

	@Override
	public void run()
	{
		glfwMakeContextCurrent(window.getWindowID());
		File vertexShader = new File("src/com/github/jannled/engine/shader/vertexShader.glsl");
		File fragmentShader = new File("src/com/github/jannled/engine/shader/fragmentShader.glsl");
		Shader vshader = new Shader(GL_VERTEX_SHADER, vertexShader);
		Shader fshader = new Shader(GL_FRAGMENT_SHADER, fragmentShader);
		
		Shaderprogram shaderprograme = new Shaderprogram(vshader, fshader);
		window.getRenderer().setShaderPrograme(shaderprograme);
	}
}
