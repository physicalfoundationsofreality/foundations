package com.gruber.pfr.space.vectors;

public interface SubSpace {

	public VectorSpace getBaseSpace();
	
	public void setBaseSpace(VectorSpace space);
	
	public Vector getProjection(Vector vector);
}
