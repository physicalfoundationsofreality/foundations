package com.gruber.pfr.space.rings;

import com.gruber.pfr.space.base.Set;

public abstract class RingElement implements Set {

	Ring ring;

	public RingElement(Ring ring) {
		this.ring = ring;
	}

	public Ring getRing() {
		return ring;
	}

	public boolean isElement(Set set) {
		return false;
	}
	
	public Set getRandomElement() {
		return null;
	}
	
	public RingElement add(RingElement el) {
		
		return (RingElement)this.ring.add(this, el);
	}
	public RingElement multiply(RingElement el) {
		
		return (RingElement)this.ring.multiply(this, el);
	}
}
