package com.gruber.pfr.space.vectors.knspaces;

import com.gruber.pfr.space.rings.RingElement;
import com.gruber.pfr.space.vectors.FiniteDimensionalVectorSpaceBasis;
import com.gruber.pfr.space.vectors.Vector;

public class KnStandardBasis extends FiniteDimensionalVectorSpaceBasis {

	public KnStandardBasis(KnSpace space) {
		super(space,null,null);
		
		KnVector[] basis = new KnVector[space.getDim()];
		for(int i = 0; i < space.getDim(); i++)
			basis[i] = space.getStandardBasisElement(i);
		this.setBaseVectors(basis);

	}

	public RingElement getCoordinate(Vector baseVector, Vector vec) {

		return ((KnSpace)this.getBaseSpace()).innerProduct.operate(baseVector, vec);
	}
}
