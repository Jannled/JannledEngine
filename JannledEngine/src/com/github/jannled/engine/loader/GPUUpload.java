package com.github.jannled.engine.loader;

/**
 * If the class is uploading something to the GPU
 * @author Jannled
 *
 */
public interface GPUUpload
{
	public abstract void toGPU(int vaoID);
}
