package com.github.jannled.engine.scene;

import com.github.jannled.lib.math.Matrix;
import com.github.jannled.lib.math.Vector;

public class Camera extends SceneObject
{
	Matrix viewMatrix;
	Matrix projectionMatrix;
	
	public Camera(Vector position, Vector rotation)
	{
		super(position);
		super.setRotation(rotation);
	}

	@Override
	protected void init()
	{
		
	}

	@Override
	public void render()
	{
		
	}

}
