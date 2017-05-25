package com.gruber.pfr.space.numbers.real;

import java.util.Random;

import com.gruber.pfr.space.base.AdditiveSpace;
import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.numbers.complex.ComplexNumber;
import com.gruber.pfr.space.rings.Field;

public class RealNumbers extends Field {
	
	static RealNumbers instance = null;
	
	Random random = new Random(System.currentTimeMillis());
	
	public Set getRandomElement() {
		
		return new RealNumber(random.nextFloat() * random.nextInt());
	}
	
	public static RealNumbers getInstance() {
		
		if(instance == null) 
			instance =  new RealNumbers();
		
		return instance;
	}
	
	protected RealNumbers() {

		super(new RealAddition(), new RealMultiplication());
	}

	public boolean isElement(Set set) {

		try {
			RealNumber num = (RealNumber)set;
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public AdditiveSpace getAdditiveGroup() {

		return this;
	}

	public Set getNullElement() {
		return new RealNumber(0);
	}

	public Set getNegative(Set element) {
		
		try {
			RealNumber num = (RealNumber)element;
			return new RealNumber(-num.base);
					
		} catch(Exception e) {
			return null;
		}
	}

	public Set getOneElement() {

		return new RealNumber(1);
	}

	public Set getInverse(Set element) {

		try {
			RealNumber num = (RealNumber)element;
			return new RealNumber(1/num.base);
					
		} catch(Exception e) {
			return null;
		}
	}
}
