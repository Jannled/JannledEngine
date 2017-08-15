package com.github.jannled.engine.scene;

import com.github.jannled.engine.maths.Position;

public abstract class SceneObject
{
	protected Position pos;
	
	public SceneObject(Position p)
	{
		this.pos = p;
	}
}
