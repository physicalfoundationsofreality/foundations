package com.gruber.pfr.space.vectors.knspaces;

import com.gruber.pfr.space.base.AutoOperation;
import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.rings.Field;
import com.gruber.pfr.space.rings.RingElement;
import com.gruber.pfr.space.vectors.FiniteDimensionalVectorSpace;
import com.gruber.pfr.space.vectors.ScalarMultiplication;

public class KnSpace extends FiniteDimensionalVectorSpace {

	protected KnSpace(Field baseField, int dim) {
		super(baseField, new KnAddition(), new KnScalarMultiplication(), dim);
		((AutoOperation)this.getAddition()).setBase(this);
		((ScalarMultiplication)this.getMultiplication()).setBase(this);
	}

	public Set getNullElement() {

		try {
			if (this.getBaseRing().getNullElement() == null)
				return null;

			RingElement[] elements = new RingElement[this.getDim()];
			for (int i = 0; i < elements.length; i++)
				elements[i] = (RingElement) this.getBaseRing().getNullElement();

			return new KnVector(this, elements);
		} catch (Exception e) {
			return null;
		}
	}

	public boolean isElement(Set set) {
		try {
			KnVector vec = (KnVector) set;
			KnSpace space = (KnSpace) vec.getSpace();
			if (space.getDim() == this.getDim() && space.getBaseRing() == this.getBaseRing())
				return true;
		} catch (Exception e) {
		}
		return false;
	}

	public Set getRandomElement() {
		try {
			RingElement[] elements = new RingElement[this.getDim()];

			for (int i = 0; i < this.getDim(); i++) {
				elements[i] = (RingElement)this.getBaseRing().getRandomElement();
			}

			return new KnVector(this, elements);
		} catch (Exception e) {
			return null;
		}
	}
}
