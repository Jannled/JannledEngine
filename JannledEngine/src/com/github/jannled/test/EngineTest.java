package com.github.jannled.test;

import java.util.Arrays;

import com.github.jannled.engine.Renderer;
import com.github.jannled.engine.Renderlooper;
import com.github.jannled.engine.Window;
import com.github.jannled.engine.scene.Model;
import com.github.jannled.engine.scene.Scene;
import com.github.jannled.engine.shader.Shader;
import com.github.jannled.engine.shader.Shaderprogram;
import com.github.jannled.lib.Print;
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
		new EngineTest();
	}

	public void run()
	{
		glfwMakeContextCurrent(window.getWindowID());
	}
	
	@Override
	public void post(Renderer renderer)
	{
		//File vertexShader = new File("src/com/github/jannled/engine/shader/vertexShader.glsl");
		//File fragmentShader = new File("src/com/github/jannled/engine/shader/fragmentShader.glsl");
		Shader vshader = new Shader(GL_VERTEX_SHADER, Test.vshader);
		Shader fshader = new Shader(GL_FRAGMENT_SHADER, Test.fshader);
		
		Shaderprogram shaderprograme = new Shaderprogram(vshader, fshader);
		renderer.setShaderPrograme(shaderprograme);
		
		Scene scene = new Scene();
		renderer.setScene(scene);
		
		Model m = new Model(Test.triangle);
		m.upload();
		scene.addSceneObject(m);
	}

	@Override
	public void frame()
	{
		
	}
}
