package com.gruber.pfr.space.numbers.real;

import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.numbers.complex.ComplexNumber;
import com.gruber.pfr.space.rings.RingElement;

public class RealNumber extends RingElement {

	float base;

	public RealNumber(float base) {

		super(RealNumbers.getInstance());
		
		this.base = base;
	}

	public float getBase() {
		return base;
	}

	public boolean isElement(Set set) {
		return false;
	}
	
	public boolean equals(Object obj) {
		
		try {
			RealNumber num = (RealNumber) obj;
			if (base == num.base)
				return true;
		} catch (Exception e) {
		}
		
		return false;
	}
}
