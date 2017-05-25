package com.gruber.pfr.space.base;

public interface Map {

	public Set getDomain();
	
	public Set getRange();
	
	public abstract Set getImage(Set orig);
}
