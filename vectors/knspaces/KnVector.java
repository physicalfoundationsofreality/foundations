package com.gruber.pfr.space.vectors.knspaces;

import com.gruber.pfr.space.rings.RingElement;
import com.gruber.pfr.space.vectors.FiniteDimensionalVectorSpace;
import com.gruber.pfr.space.vectors.Module;
import com.gruber.pfr.space.vectors.Vector;

public class KnVector extends Vector {
	
	RingElement[] elements;
	
	public class InvalidElementsException extends Exception {};
	
	public KnVector(Module space, RingElement[] elements) throws InvalidElementsException {
		super(space);
		
		if(elements.length != ((FiniteDimensionalVectorSpace)space).getDim())
			throw new InvalidElementsException();
		
		for(int i = 0; i < elements.length; i++) 
			if(!space.getBaseRing().isElement(elements[i]))
				throw new InvalidElementsException();
	}

	public RingElement[] getElements() {
		return elements;
	}
}
