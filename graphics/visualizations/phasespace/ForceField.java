package com.gruber.pfr.graphics.visualizations.phasespace;

import com.gruber.pfr.graphics.elements.RealBasedVector;
import com.gruber.pfr.graphics.visualizations.realbased.RealBasedVectorField;
import com.gruber.pfr.space.numbers.real.RealVector;
import com.gruber.pfr.space.vectors.knspaces.phasespace.PhaseVector;

public abstract class ForceField implements RealBasedVectorField {
	
	int dimension;
	
	public ForceField(int dimension) {
		this.dimension = dimension;
	}
	
	public abstract RealBasedVector getVector(PhaseVector vector);

	public RealBasedVector getVector(RealVector origin) {

		return this.getVector((PhaseVector)origin);
	}
}
