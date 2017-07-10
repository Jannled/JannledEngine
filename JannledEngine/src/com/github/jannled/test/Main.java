package com.github.jannled.test;

import com.github.jannled.engine.Window;
import com.github.jannled.engine.loader.OBJLoader;
import com.github.jannled.engine.scene.Model;
import com.github.jannled.engine.scene.Scene;
import com.github.jannled.engine.shader.Shader;

import static org.lwjgl.opengl.GL20.*;

import java.io.File;

public class Main
{
	Window window;
	
	private Main()
	{
		window = new Window("Test Version of the JannledEngine", 1280, 720);
		
		//File vertexShader = new File("src/com/github/jannled/engine/shader/vertexShader.glsl");
		//File fragmentShader = new File("src/com/github/jannled/engine/shader/fragmentShader.glsl");
		//Shader vshader = new Shader(GL_VERTEX_SHADER, vertexShader);
		//Shader fshader = new Shader(GL_FRAGMENT_SHADER, fragmentShader);
		
		//Scene scene = window.getRenderer().getScene();
		//Model model = OBJLoader.loadModel(Main.class.getResourceAsStream("Suzanna.obj"));
		//scene.addSceneObject(model);

	}
	
	public static void main(String[] args)
	{
		new Main();
	}

	
}
