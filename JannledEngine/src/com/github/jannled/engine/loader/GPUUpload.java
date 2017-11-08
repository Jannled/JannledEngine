package com.github.jannled.engine.loader;

/**
 * If the class is uploading something to the GPU
 * @author Jannled
 */
public interface GPUUpload
{
	/**
	 * Upload the data to the GPU. The VAO entries need to be enabled but the array is already allocated.
	 */
	public abstract void toGPU(int vaoID);
}