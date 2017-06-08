package com.gruber.pfr.space.vectors;

import com.gruber.pfr.space.modules.LinearIndependentSet;
import com.gruber.pfr.space.rings.RingElement;

public interface VectorSpaceBasis extends LinearIndependentSet {

	
	public RingElement getCoordinate(Vector baseVector, Vector vector);
}