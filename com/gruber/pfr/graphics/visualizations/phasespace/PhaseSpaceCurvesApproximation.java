package com.gruber.pfr.graphics.visualizations.phasespace;

import com.gruber.pfr.graphics.elements.RealBasedVector;
import com.gruber.pfr.graphics.visualizations.realbased.AdvancedNumericCurvesApproximation;
import com.gruber.pfr.graphics.visualizations.realbased.RealBasedVectorField;
import com.gruber.pfr.space.numbers.real.RealVector;
import com.gruber.pfr.space.vectors.knspaces.KnVector.InvalidElementsException;
import com.gruber.pfr.space.vectors.knspaces.phasespace.PhaseSpace;
import com.gruber.pfr.space.vectors.knspaces.phasespace.PhaseVector;

public class PhaseSpaceCurvesApproximation extends AdvancedNumericCurvesApproximation {

	PhaseSpace space = null;

	public PhaseSpaceCurvesApproximation(RealVector[] startingPositions, RealVector[] startingVelocities, int length,
			int granularity, int dimension, RealBasedVectorField field) {

		super(null, length, granularity, 2 * dimension, field);

		try {
			this.space = new PhaseSpace(dimension);

			RealBasedVector[] start = new RealBasedVector[startingPositions.length];
			for (int i = 0; i < startingPositions.length; i++) {
				PhaseVector vector = new PhaseVector(this.space, startingPositions[i].getElements(),
						startingVelocities[i].getElements());
				start[i] = field.getVector(vector);
			}
			this.setStartingPoints(start);

		} catch (InvalidElementsException e) {
			e.printStackTrace();
		}
	}
}
