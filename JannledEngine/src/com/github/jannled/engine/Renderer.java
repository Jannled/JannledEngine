package com.github.jannled.engine;

import java.io.File;

import com.github.jannled.engine.hid.InputHandler;
import com.github.jannled.engine.model.loader.ModelLoader;
import com.github.jannled.engine.scenegraph.Scene;
import com.github.jannled.engine.shaders.ShaderLoader;
import com.github.jannled.lib.Print;
import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

/**
 * Main class for the OpenGL renderer engine. It creates the OpenGL Canvas, where
 * the game is renderer on. You need to put the canvas into a JFrame in order to
 * see whats going one. You can do this by calling the method <i>getGLCanvas()</i>.
 * @author Jannled
 * @version 0.0.1
 * @see com.github.jannled.engine.Renderer#getGLCanvas
 */
public class Renderer implements GLEventListener
{
	Main main;
	
	final GLProfile profile = GLProfile.get("GL2");
	final GLCapabilities capabilities = new GLCapabilities(profile);
	final GLCanvas canvas = new GLCanvas(capabilities);
	GL4 gl;
	
	int width = 1280;
	int height = 720;
	int fps = 60;
	
	FPSAnimator animator;
	ModelLoader modelLoader;
	ShaderLoader shaderLoader;
	Renderpipeline renderpipeline;
	InputHandler inputHandler;
	
	Scene scene = new Scene("Hauptscene");
	
	public Renderer(Main main, int width, int heigth, int fps)
	{
		Print.m("Preparing renderer. Resolution: " + width + "x" + height + ". FPS: " + fps + ".");
		this.main = main;
		this.width = width;
		this.height = heigth;
		this.fps = fps;
		canvas.setSize(width, height);
		canvas.addGLEventListener(this);
	}
	
	@Override
	public void init(GLAutoDrawable drawable) 
	{
		Print.m("Initlialising OpenGL Canvas.");
		gl = drawable.getGL().getGL4();
		renderpipeline = new Renderpipeline(this);
		
		//Print some debug stuff about the system
		Print.m("Operating System: " + System.getProperty("os.name"));
		Print.m("OpenGL Renderer: " + gl.glGetString(GL2.GL_RENDERER));
		Print.m("OpenGL Version: " + gl.glGetString(GL2.GL_VERSION));
		Print.m("JOGL Version: " + "2.3.2");
		Print.m("Java Version: " + System.getProperty("java.version"));
		
		//Setup some basic stuff
		capabilities.setDoubleBuffered(true);
		capabilities.setHardwareAccelerated(true);
		gl.glClearColor(1.0f, 0.0f, 1.0f, 1.0f);
		gl.glEnable(GL4.GL_MULTISAMPLE);
		
		//Setup input listener
		inputHandler = new InputHandler(canvas);
		
		animator = new FPSAnimator(canvas, fps);
		animator.start();
		drawable.getAnimator().setUpdateFPSFrames(3, null);
		
		setupModels();
		setupShaders();
	}
	
	@Override
	public void dispose(GLAutoDrawable drawable)
	{
		Print.m("Destroying OpenGL Canvas.");
		for(int vao : modelLoader.getVAOS())
		{
			gl.glDeleteVertexArrays(1, Buffers.newDirectIntBuffer(new int[] {vao}));
		}
	}
	
	@Override
	public void display(GLAutoDrawable drawable) 
	{
		renderpipeline.renderFrame(scene);
	}
	
	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) 
	{
		Print.m("Window size changed to " + width + "x" + height + ".");
	}
	
	public void setupModels()
	{
		Print.m("Loading Models...");
		modelLoader = new ModelLoader(gl);
		scene.addToScene(modelLoader.load(new File("src/com/github/jannled/engine/assets/models/Suzanne.obj")));
	}
	
	public void setupShaders()
	{
		Print.m("Loading Shaders...");
		shaderLoader = new ShaderLoader(gl);
		shaderLoader.loadShaders("/com/github/jannled/engine/shaders/VertexShader.glsl", "/com/github/jannled/engine/shaders/FragmentShader.glsl");
	}
	
	/**
	 * This method is necesarry to embed the GLCanvas into your JFrame. This class
	 * doesnt handle the Window, its just creating the canvas where the whole game 
	 * renders on.
	 * @return The OpenGL canvas, where the whole rendering process takes place.
	 */
	public GLCanvas getGLCanvas()
	{
		return canvas;
	}
	
	public GL4 getGL()
	{
		return gl;
	}
	
	public Main getMain()
	{
		return main;
	}
	
}
