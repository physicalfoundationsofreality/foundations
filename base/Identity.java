package com.gruber.pfr.space.base;

public class Identity implements Injection {

	Set set;
	
	public Identity(Set set) {
		this.set = set;
	}

	public Set getDomain() {
		return set;
	}

	public Set getRange() {
		return set;
	}

	public Set getImage(Set orig) {
		return set;
	}

	public Set getPreImage(Set orig) {
		return set;
	}
}
