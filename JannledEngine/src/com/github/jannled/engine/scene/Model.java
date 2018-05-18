package com.github.jannled.engine.scene;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import java.nio.DoubleBuffer;

import com.github.jannled.lib.Print;
import com.github.jannled.lib.math.Matrix;
import com.github.jannled.lib.math.Vector;

public class Model extends SceneObject
{
	private double[] vertices;
	private int verticesID;
	
	private int[] faces;
	private int indicesID;
	
	private int drawType;
	
	public Model(Vector position, double[] vertices, int[] faces)
	{
		super(position);
		this.vertices = vertices;
		this.faces = faces;
	}
	
	@Override
	protected void init()
	{
		DoubleBuffer verticeBuffer = DoubleBuffer.wrap(vertices);
		
		verticesID = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, verticesID);
		glBufferData(GL_ARRAY_BUFFER, verticeBuffer, GL_STATIC_DRAW);
		
		glEnableVertexAttribArray(0);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
		
		indicesID = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indicesID);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, faces, GL_STATIC_DRAW);
	}

	@Override
	public void render()
	{
		int mHandle = shaderprogram.getAttributeID("transform");
		
		//Matrix model = Matrix.scale(getScale()).multiply(Matrix.rotate(getRotation())).multiply(Matrix.translate(getPosition())).multiply(Matrix.perspective(0.01, 100, 60, 16/9));
		//Matrix model = Matrix.scale(getScale()).multiply(Matrix.rotate(getRotation())).multiply(Matrix.translate(getPosition()));
		//Matrix model = Matrix.perspective(0.01, 10, 45, 1.7777778);
		//Matrix testPos = model.multiply(new Matrix(new double[][] {{2}, {3}, {4}, {1}}));
		Matrix model = Matrix.scale(getScale());
		shaderprogram.setMatrix(mHandle, model);
		
		glBindVertexArray(getVAO());
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indicesID);
		glDrawElements(drawType, faces.length, GL_UNSIGNED_INT, 0);
	}
	
	public int getDrawType()
	{
		return drawType;
	}
	
	public double[] getVertices()
	{
		return vertices;
	}
	
	public void setDrawType(int drawType)
	{
		if(drawType == GL_POINTS || drawType == GL_LINE_STRIP || drawType == GL_LINE_LOOP || drawType == GL_LINES || 
				drawType == GL_TRIANGLE_STRIP || drawType == GL_TRIANGLE_FAN || drawType == GL_TRIANGLES)
		{
			this.drawType = drawType;
		}
		else
		{
			Print.e("The specified drawtype '" + drawType + "'is invalid! See glDrawElements() for reference!");
		}
	}
	
	@Override
	public String toString()
	{
		return "Model(ID: " + vaoID + ")";
	}
}
