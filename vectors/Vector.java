package com.gruber.pfr.space.vectors;

import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.rings.RingElement;

public abstract class Vector implements Set, Cloneable {
	
	Module space;
	
	public Vector clone() {
		
		try {
			return (Vector)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

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

	public void setSpace(Module space) {
		this.space = space;
	}
}
