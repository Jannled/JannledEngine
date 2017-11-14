package com.github.jannled.engine.scene;

import com.github.jannled.engine.Renderer;
import com.github.jannled.engine.loader.GPUUpload;
import com.github.jannled.engine.maths.Position;
import com.github.jannled.lib.Print;

import static org.lwjgl.opengl.GL30.*;

public abstract class SceneObject implements GPUUpload
{
	protected Position pos;
	protected int vaoID;
	protected boolean uploaded = false; 
	
	public SceneObject(GPUUploader uploader, Position p)
	{
		uploader.addToQue(this);
		this.pos = p;
	}
	
	/**
	 * Called by the thread currently communicating with the OpenGL interface. Needs to be done because there 
	 * can only be one Thread locking the GL interface.
	 */
	void createGLObject()
	{
		vaoID = glGenVertexArrays();
		Print.d("Loading Ressource " + vaoID);
		glBindVertexArray(vaoID);
		toGPU(vaoID);
		glBindVertexArray(0);
		uploaded = true;
	}
	
	/**
	 * Called to render this specific object.
	 * @param caller The render loop instance calling the render method.
	 */
	public abstract void render(Renderer caller);
	
	/**
	 * If the Scene Object is alreay uploaded to the GPU
	 * @return True if the data has been uploaded successfully.
	 */
	public boolean isUploaded()
	{
		return uploaded;
	}
}
