package com.gruber.pfr.space.vectors;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

import com.gruber.pfr.space.base.Set;
import com.gruber.pfr.space.modules.Module;
import com.gruber.pfr.space.rings.RingElement;

public class FiniteDimensionalVectorSpaceBasis implements VectorSpaceBasis {

	FiniteDimensionalVector[] baseVectors;
	FiniteDimensionalVectorSpace space;
	Random rand = new Random(System.currentTimeMillis());

	Method getCoordinate;

	public FiniteDimensionalVectorSpaceBasis(FiniteDimensionalVectorSpace space, FiniteDimensionalVector[] baseVectors,
			Method getCoordinate) {
		// super(space);

		this.baseVectors = baseVectors;
		this.getCoordinate = getCoordinate;
	}

	public FiniteDimensionalVector[] getBaseVectors() {
		return baseVectors;
	}
	
	public void setBaseVectors(FiniteDimensionalVector[] baseVectors) {
		this.baseVectors = baseVectors;
	}

	public RingElement getCoordinate(Vector baseVector, Vector vector) {

		try {
			return (RingElement) this.getCoordinate.invoke(baseVector, vector);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}
	}

	public RingElement getCoordinate(int position, Vector vector) {
		return this.getCoordinate(baseVectors[position], vector);
	}

	public Module getBaseSpace() {
		return this.space;
	}

	public void setBaseSpace(Module space) {

		this.space = (FiniteDimensionalVectorSpace) space;
	}

	public boolean isElement(Set set) {

		boolean isEl = false;

		for (int i = 0; i < this.baseVectors.length; i++)
			if (this.baseVectors[i].equals((Vector) set))
				return true;

		return isEl;
	}

	public Set getRandomElement() {

		return this.baseVectors[rand.nextInt(this.space.getDim())];
	}

	// Resort by exchanging two basis elements
	public void resortBasis(int i, int j) {

		FiniteDimensionalVector veci = this.baseVectors[i];
		this.baseVectors[i] = this.baseVectors[j];
		this.baseVectors[j] = veci;
	}

	// scale and add vector i with vector j, i not j
	public void modifyVector(int i, int j, RingElement scalar) {

		if (i == j)
			return;

		this.baseVectors[i] = (FiniteDimensionalVector) this.baseVectors[i].add(baseVectors[j].multiply(scalar));
	}

	public FiniteDimensionalVectorSpaceBasis getSubspaceBasis(FiniteDimensionalVector[] baseVectors,
			FiniteDimensionalVectorSpaceBasis supBasis) {

		FiniteDimensionalVectorSpace space;
		space = (FiniteDimensionalVectorSpace) ((VectorSpace) this.getBaseSpace()).clone();

		try {
			return new FiniteDimensionalVectorSpaceBasis(space, baseVectors, supBasis.getClass().getMethod("getCoordinates", Vector.class,Vector.class));
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			return null;
		}
	}
}
