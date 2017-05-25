package com.gruber.pfr.space.numbers.complex;

import java.util.Random;

import com.gruber.pfr.space.base.AdditiveSpace;
import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.rings.Field;

public class ComplexNumbers extends Field {
	
	static ComplexNumbers instance = null;
	
	Random random = new Random(System.currentTimeMillis());
	
	public Set getRandomElement() {
		
		return new ComplexNumber(random.nextFloat() * random.nextInt(), random.nextFloat() * random.nextInt());
	}
	
	public static ComplexNumbers getInstance() {
		
		if(instance == null) 
			instance =  new ComplexNumbers();
		
		return instance;
	}
	
	protected ComplexNumbers() {

		super(new ComplexAddition(), new ComplexMultiplication());
	}

	public boolean isElement(Set set) {

		try {
			ComplexNumber num = (ComplexNumber)set;
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public AdditiveSpace getAdditiveGroup() {

		return this;
	}

	public Set getNullElement() {
		return new ComplexNumber(0,0);
	}

	public Set getNegative(Set element) {
		
		try {
			ComplexNumber num = (ComplexNumber)element;
			return new ComplexNumber(-num.re,-num.im);
					
		} catch(Exception e) {
			return null;
		}
	}

	public Set getOneElement() {

		return new ComplexNumber(1,0);
	}

	public Set getInverse(Set element) {

		try {
			ComplexNumber num = (ComplexNumber)element;
			float x = num.re / (num.re * num.re + num.im * num.im);
			float y =  - num.im / (num.re * num.re + num.im * num.im);
			return new ComplexNumber(x,y);
					
		} catch(Exception e) {
			return null;
		}
	}
}
