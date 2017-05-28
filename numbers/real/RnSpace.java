package com.gruber.pfr.space.numbers.real;

import java.util.Arrays;

import com.gruber.pfr.space.vectors.knspaces.KnSpace;

public class RnSpace extends KnSpace {

	static RnSpace[] RnSpaces = new RnSpace[20];
	
	protected RnSpace(int dim) {
		super(RealNumbers.instance, dim);
	}
	
	public static KnSpace getInstance(int dim) {
		
		if(RnSpaces.length < dim + 1)
			RnSpaces = Arrays.copyOf(RnSpaces, dim + 1);
		
		if(RnSpaces[dim] == null)
			RnSpaces[dim] = new RnSpace(dim);
		
		return RnSpaces[dim];
	}
}