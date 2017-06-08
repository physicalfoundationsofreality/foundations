package com.gruber.pfr.space.base.map;

import com.gruber.pfr.space.base.Set;

public interface Map {

	public Set getDomain();
	
	public Set getRange();
	
	public abstract Set getImage(Set orig);
	
	public abstract Set getPreImage(Set image);
}
