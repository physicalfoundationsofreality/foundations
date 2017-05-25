package com.gruber.pfr.space.base;

public class QuotientProjection implements Map {

	QuotientSpace quotient;
	
	public QuotientProjection(QuotientSpace quotient) {
		this.quotient = quotient;
	}

	public Set getDomain() {
		
		return quotient.relat.base;
	}

	public Set getRange() {
		
		return quotient;
	}

	public Set getImage(Set orig) {

		return this.quotient.relat.getEquivalenceClass(orig);
	}
}
