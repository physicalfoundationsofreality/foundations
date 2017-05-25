package com.gruber.pfr.space.vectors;

import com.gruber.pfr.space.base.AdditiveSpace;
import com.gruber.pfr.space.base.AutoOperation;
import com.gruber.pfr.space.base.Operation;
import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.rings.Ring;
import com.gruber.pfr.space.rings.RingElement;

public abstract class Module implements AdditiveSpace {

	Ring baseRing;
	ScalarMultiplication multiplication;
	AutoOperation addition;

	protected Module(Ring baseRing,AutoOperation addition,ScalarMultiplication multiplication) {

		this.baseRing = baseRing;
		this.multiplication = multiplication;
		this.addition = addition;
	}

	public ScalarMultiplication getMultiplication() {
		return multiplication;
	}

	public void setMultiplication(ScalarMultiplication multiplication) {
		this.multiplication = multiplication;
	}

	public void setAddition(AutoOperation addition) {
		this.addition = addition;
	}

	public Ring getBaseRing() {
		return baseRing;
	}
	
	public Set add(Set op1, Set op2) {

		try {
			return this.getAddition().operate(op1, op2);
		} catch (Exception e) {
			return null;
		}
	}

	public Set getNegative(Set element) {
		try {

			RingElement negOne = (RingElement) this.baseRing.getNegative(this.baseRing.getOneElement());
			return this.multiply(negOne, (Vector) element);

		} catch (Exception e) {
			return null;
		}
	}

	public Operation getAddition() {

		return this.getAddition();
	}

	public Vector multiply(RingElement el, Vector vector) {
		try {

			return (Vector) this.multiplication.operate(el, vector);

		} catch (Exception e) {
			return null;
		}
	}
}
