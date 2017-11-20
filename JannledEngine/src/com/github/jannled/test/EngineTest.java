package com.github.jannled.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;

import com.github.jannled.engine.Renderer;
import com.github.jannled.engine.Renderlooper;
import com.github.jannled.engine.Window;
import com.github.jannled.engine.loader.OBJLoader;
import com.github.jannled.engine.scene.Model;
import com.github.jannled.engine.scene.Scene;
import com.github.jannled.engine.shader.Shader;
import com.github.jannled.engine.shader.Shaderprogram;
import com.github.jannled.lib.Print;
import com.github.jannled.lib.math.Vector;
import com.github.jannled.test.testengine.Test;

import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.opengl.GL20.*;

public class EngineTest implements Renderlooper
{
	private Window window;
	
	public EngineTest()
	{
		window = new Window(this, "JannledEngine", 1280, 720);
	}
	
	public static void main(String[] args)
	{
		Print.setOutputLevel(Print.ALL);
		Print.d(Arrays.toString(args));
		Thread.currentThread().setName("Renderer");
		new EngineTest();
	}

	public void run()
	{
		glfwMakeContextCurrent(window.getWindowID());
	}
	
	@Override
	public void post(Renderer renderer)
	{
		File vertexShader = new File("src/com/github/jannled/engine/shader/vertexShader.glsl");
		File fragmentShader = new File("src/com/github/jannled/engine/shader/fragmentShader.glsl");
		Shader vshader = new Shader(GL_VERTEX_SHADER, vertexShader);
		Shader fshader = new Shader(GL_FRAGMENT_SHADER, fragmentShader);
		
		Shaderprogram shaderprograme = new Shaderprogram(vshader, fshader);
		renderer.setShaderPrograme(shaderprograme);
		
		Scene scene = new Scene();
		renderer.setScene(scene);
		
		Model m = null;
		try
		{
			m = OBJLoader.loadModel(new BufferedReader(new FileReader(new File("src/com/github/jannled/test/Suzanna.obj"))));
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} 
		
		m = new Model(new Vector(0, 0, 1), Test.triangle, new int[] {0, 1, 2});
		m.setScale(0.5, 0.5, 0.5);
		m.upload(renderer.getShaderPrograme());
		scene.addSceneObject(m);
	}

	@Override
	public void frame()
	{
		
	}
}
