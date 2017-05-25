package com.gruber.pfr.space.vectors;

import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.rings.RingElement;

public abstract class Vector implements Set {
	
	Module space;

	public Vector(Module space) {
		this.space = space;
	}

	public boolean isElement(Set set) {
		return false;
	}
	
	public Set getRandomElement() {
		return null;
	}
	
	public Vector add(Vector el) {
		
		return (Vector)this.space.add(this, el);
	}
	public Vector multiply(RingElement scalar) {
		
		return this.space.multiply(scalar, this);
	}

	public Module getSpace() {
		return space;
	}
}
