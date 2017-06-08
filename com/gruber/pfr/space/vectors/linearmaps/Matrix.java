package com.gruber.pfr.space.vectors.linearmaps;

import com.gruber.pfr.space.rings.Ring;

public interface Matrix {

	public Ring getBaseRing();
	
	public void setBaseRing(Ring baseRing);
	
	public Matrix getTransposed();
}