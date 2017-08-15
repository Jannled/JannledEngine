package com.github.jannled.test;

import com.github.jannled.engine.Window;
import com.github.jannled.engine.loader.OBJLoader;
import com.github.jannled.engine.scene.Model;
import com.github.jannled.engine.scene.Scene;
import com.github.jannled.engine.shader.Shader;
import com.github.jannled.lib.Print;

import static org.lwjgl.opengl.GL20.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main
{
	Window window;
	
	private Main()
	{
		window = new Window("Test Version of the JannledEngine", 1280, 720);
		window.getRenderer().setScene(new Scene());
		File vertexShader = new File("src/com/github/jannled/engine/shader/vertexShader.glsl");
		File fragmentShader = new File("src/com/github/jannled/engine/shader/fragmentShader.glsl");
		Shader vshader = new Shader(GL_VERTEX_SHADER, vertexShader);
		Shader fshader = new Shader(GL_FRAGMENT_SHADER, fragmentShader);
		
		Scene scene = window.getRenderer().getScene();
		File m = new File("src/com/github/jannled/test/Suzanna.obj");
		Print.m("" + m.getAbsolutePath() + (m.exists() ? " ist vorhanden" : " ist nicht vorhanden"));
		Model model = null;
		try
		{
			model = OBJLoader.loadModel(new BufferedReader(new FileReader(m)));
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		scene.addSceneObject(model);

	}
	
	public static void main(String[] args)
	{
		new Main();
	}

	
}
