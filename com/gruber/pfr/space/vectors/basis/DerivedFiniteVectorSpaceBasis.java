package com.gruber.pfr.space.vectors.basis;

import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.rings.RingElement;
import com.gruber.pfr.space.vectors.FiniteDimensionalVector;
import com.gruber.pfr.space.vectors.FiniteDimensionalVectorSpace;
import com.gruber.pfr.space.vectors.Vector;
import com.gruber.pfr.space.vectors.VectorSpace;

public class DerivedFiniteVectorSpaceBasis extends FiniteDimensionalVectorSpaceBasis {

//	VectorSpaceBasis supBasis;

	public DerivedFiniteVectorSpaceBasis(Vector[] baseVectors, FiniteDimensionalVectorSpace span) {

		super(span, baseVectors);
//		this.supBasis = supBasis;
	}

	public RingElement getCoordinate(Vector baseVector, Vector vector) {
		return null;
	}

	@Override
	public boolean isElement(Set set) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set getRandomElement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VectorSpace getBaseSpace() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setBaseSpace(VectorSpace space) {
		// TODO Auto-generated method stub
		
	}
}
