package com.github.jannled.engine.scene;

import com.github.jannled.engine.maths.Position;

public abstract class SceneObject
{
	Position p;
	
	public SceneObject(Position p)
	{
		this.p = p;
	}
}
