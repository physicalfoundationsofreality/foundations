package com.gruber.pfr.space.vectors;

import com.gruber.pfr.space.base.AutoOperation;
import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.rings.Field;

public class VectorSpan extends FiniteDimensionalVectorSpace implements SubSpace {

	FiniteDimensionalVectorSpaceBasis basis;
	VectorSpace baseSpace;
	
	public VectorSpan(FiniteDimensionalVector[] baseVectors, VectorSpace supSpace) {

		super((Field) baseVectors[0].getSpace().getBaseRing(), (AutoOperation) supSpace.getAddition(), supSpace.getMultiplication(), baseVectors.length);
		this.basis = new FiniteDimensionalVectorSpaceBasis(this, baseVectors, null);
		this.baseSpace = supSpace;
	}

	public Set getNullElement() {
		return this.baseSpace.getNullElement();
	}

	public VectorSpaceBasis getStandardBasis() {
		return this.basis;
	}

	public void setStandardBasis(VectorSpaceBasis standardBasis) {
		this.basis = (FiniteDimensionalVectorSpaceBasis)standardBasis;
	}

	public boolean isElement(Set set) {
		
		Vector vec = (Vector)set;
		Vector proj = this.getProjection(vec);
		return vec.equals(proj);
	}

	public Set getRandomElement() {

		return this.basis.getRandomElement();
	}
	
	public FiniteDimensionalVectorSpaceBasis getBasis() {
		return basis;
	}

	public void setBasis(FiniteDimensionalVectorSpaceBasis basis) {
		this.basis = basis;
	}

	public Vector getProjection(Vector vector) {
		
		Vector vec = (Vector)this.getNullElement();
		for(int i = 0; i < this.getDim(); i++) {
			vec.add(this.basis.getBaseVectors()[i].multiply(this.basis.getCoordinate(i, vector)));
		}
		return vec;
	}

	public VectorSpace getBaseSpace() {
		return baseSpace;
	}

	public void setBaseSpace(VectorSpace baseSpace) {
		this.baseSpace = baseSpace;
	}



}