package com.github.jannled.engine;

public interface Renderlooper
{
	/**
	 * Called when the initialization phase is completed.
	 */
	public abstract void post(Renderer renderer);
	
	/**
	 * Called before a frame gets rendered.
	 */
	public abstract void frame();
}
