package com.gruber.pfr.space.rings;

import com.gruber.pfr.space.base.Injection;
import com.gruber.pfr.space.base.Set;

public abstract class RingInjection extends RingMorphism implements Injection {

	public RingInjection(Ring domain, Ring range) {
		super(domain, range);
	}
}
