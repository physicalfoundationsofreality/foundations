package com.gruber.pfr.space.base.quotient;

import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.base.map.Map;

public abstract class QuotientProjection implements Map {

	QuotientSpace quotient;
	
	protected QuotientProjection(QuotientSpace quotient) {
		this.quotient = quotient;
	}

	public Set getDomain() {
		
		return quotient.getRelat().getBase();
	}

	public Set getRange() {
		
		return quotient;
	}

	public Set getImage(Set orig) {

		return this.quotient.getRelat().getEquivalenceClass(orig);
	}
}
