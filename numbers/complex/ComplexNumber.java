package com.gruber.pfr.space.numbers.complex;

import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.rings.RingElement;

public class ComplexNumber extends RingElement {

	float re;
	float im;

	public ComplexNumber(float re, float im) {

		super(ComplexNumbers.getInstance());

		this.re = re;
		this.im = im;
	}

	public float getRealPart() {
		return re;
	}

	public float getImaginaryPart() {
		return im;
	}

	public boolean isElement(Set set) {
		return false;
	}

	public boolean equals(Object obj) {

		try {
			ComplexNumber num = (ComplexNumber) obj;
			if ((re == num.re) && (im == num.im))
				return true;
		} catch (Exception e) {
		}

		return false;
	}
}
