package com.gruber.pfr.graphics.elements;

import com.gruber.pfr.space.numbers.real.RealVector;

public class RealBasedVector {

	RealVector origin;
	RealVector direction;

	public RealBasedVector(RealVector origin) {
		this.origin = origin;
	}

	public RealVector getDirection() {
		return direction;
	}

	public void setDirection(RealVector direction) {
		this.direction = direction;
	}

	public RealVector getOrigin() {
		return origin;
	}

	public void setOrigin(RealVector origin) {
		this.origin = origin;
	}

	public int[] getMaxInt() {

		int[] max = new int[origin.getDim()];
		for(int i = 0; i < origin.getDim(); i++)
			max[i] = Math.max(new Double(Math.ceil(origin.getElements()[i].add(direction.getElements()[i]).getBase())).intValue(),
				new Double(Math.ceil(origin.getElements()[i].getBase())).intValue());
		
		return max;
	}
	public int[] getMinInt()  {

	int[] min = new int[origin.getDim()];
	for(int i = 0; i < origin.getDim(); i++)
		min[i] = Math.min(new Double(Math.floor(origin.getElements()[i].add(direction.getElements()[i]).getBase())).intValue(),
			new Double(Math.floor(origin.getElements()[i].getBase())).intValue());
		
		return min;
	}
	public SimpleVector asVector() {
		
		float[] orig = new float[this.origin.getDim()];
		for(int i = 0; i < orig.length; i++)
			orig[i] = origin.getElements()[i].getBase();
		
		float[] dir = new float[this.direction.getDim()];
		for(int i = 0; i < dir.length; i++)
			dir[i] = direction.getElements()[i].getBase();
		
		
		SimpleVector vec = new SimpleVector(new SimplePoint(orig));
		vec.setDirection(new SimplePoint(dir));
		
		return vec;
	}
}
