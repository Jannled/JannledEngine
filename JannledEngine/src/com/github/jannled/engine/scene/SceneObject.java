package com.github.jannled.engine.scene;

import com.github.jannled.engine.Renderer;
import com.github.jannled.engine.maths.Position;

public abstract class SceneObject
{
	protected Position pos;
	
	public SceneObject(Position p)
	{
		this.pos = p;
	}
	
	/**
	 * Called to render this specific object.
	 * @param caller The render loop instance calling the render method.
	 */
	public abstract void render(Renderer caller);
}
