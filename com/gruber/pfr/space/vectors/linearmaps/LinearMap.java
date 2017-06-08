package com.gruber.pfr.space.vectors.linearmaps;

import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.base.map.Map;
import com.gruber.pfr.space.modules.Module;

public abstract class LinearMap implements Map {

	Module domain;
	Module range;
	
	public LinearMap(Module domain, Module range) {
		
		this.domain = domain;
		this.range = range;
	}

	public Set getDomain() {

		return this.domain;
	}

	public Set getRange() {

		return this.range;
	}
	public abstract Module getKernel();
	
	public abstract Module getImage();
}
