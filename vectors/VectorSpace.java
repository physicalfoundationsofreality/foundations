package com.gruber.pfr.space.vectors;

import com.gruber.pfr.space.base.AutoOperation;
import com.gruber.pfr.space.rings.Field;

public abstract class VectorSpace extends Module {
	
	protected VectorSpace(Field baseField,AutoOperation addition,ScalarMultiplication multiplication) {
		
		super(baseField,addition,multiplication);
	}
}
