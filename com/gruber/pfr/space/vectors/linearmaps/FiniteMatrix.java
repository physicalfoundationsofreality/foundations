package com.gruber.pfr.space.vectors.linearmaps;

import com.gruber.pfr.space.rings.Ring;
import com.gruber.pfr.space.rings.RingElement;

public class FiniteMatrix implements Matrix {

	RingElement[][] matrix;
	Ring baseRing;
	
	public FiniteMatrix(Ring baseRing, RingElement[][] matrix) {
		super();
		this.matrix = matrix;
		this.baseRing = baseRing;
	}
	public RingElement[][] getMatrix() {
		return matrix;
	}
	public void setMatrix(RingElement[][] matrix) {
		this.matrix = matrix;
	}
	public Ring getBaseRing() {
		return baseRing;
	}
	public void setBaseRing(Ring baseSpace) {
		this.baseRing = baseSpace;
	}
	public void exchangeRows(int i, int j) {
		
		RingElement[] rowi = matrix[i];
		matrix[i] = matrix[j];
		matrix[j] = rowi;
	}
	public void exchangeColumns(int i, int j) {
		
		RingElement[] coli = new RingElement[matrix.length];
		for(int k = 0; k < matrix.length; k++)
			coli[k] = matrix[k][i];
		
		for(int k = 0; k < matrix.length; k++)
			matrix[k][i] = matrix[k][j];

		for(int k = 0; k < matrix.length; k++)
			matrix[k][j] = coli[k];
	}
	public void scaleRow(int i, RingElement scalar) {
		
		for(int k = 0; k < matrix.length; k++)
			matrix[i][k] = scalar.multiply(matrix[i][k]);
	}
	public void scaleColumn(int i, RingElement scalar) {
		
		for(int k = 0; k < matrix.length; k++)
			matrix[k][i] = scalar.multiply(matrix[k][i]);
	}
	public Matrix getTransposed() {
		
		RingElement[][] trans = new RingElement[matrix[0].length][matrix.length];
		
		for(int i = 0; i < matrix.length; i++)
			for(int j = 0; j < matrix[0].length; j++)
				trans[j][i] = matrix[i][j];
		
		return new FiniteMatrix(this.baseRing,trans);
	}
	public void addRow(int row, int addedRow, RingElement scalar) {
		
		for(int k = 0; k < matrix[row].length; k++)
			matrix[row][k] = scalar.multiply(matrix[addedRow][k]);
	}
	public void addColumn(int col, int addedCol, RingElement scalar) {
		
		for(int k = 0; k < matrix.length; k++)
			matrix[k][col] = scalar.multiply(matrix[k][addedCol]);
	}
	public RingElement getElement(int i, int j) {
		return this.matrix[i][j];
	}
}
