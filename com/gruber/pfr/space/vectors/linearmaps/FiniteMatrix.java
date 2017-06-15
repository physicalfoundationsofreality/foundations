package com.gruber.pfr.space.vectors.linearmaps;

import java.lang.reflect.Array;

import com.gruber.pfr.space.rings.Ring;
import com.gruber.pfr.space.rings.RingElement;
import com.gruber.pfr.space.vectors.FiniteDimensionalVector;

public class FiniteMatrix implements Matrix {

	RingElement[][] matrix;
	Ring baseRing;
	FiniteMatrix colNormForm;
	FiniteMatrix rowNormForm;
	FiniteMatrix colNormalizer;
	FiniteMatrix rowNormalizer;
	
	public FiniteMatrix(Ring baseRing, RingElement[][] matrix) {
		super();
		this.matrix = matrix;
		this.baseRing = baseRing;
		
		int pos = 0;
		int dimCol = this.getColumnNumber();
		int dimRow = this.getRownNumber();
		int colPos = 0;
		int rowPos = 0;

		RingElement nullEl = (RingElement) this.baseRing.getNullElement();
		RingElement oneEl = (RingElement) this.baseRing.getOneElement();

		RingElement[][] colNormalForm = matrix.clone();
		RingElement[][] rowNormalForm = matrix.clone();
		RingElement[][] colNormal = new RingElement[dimCol][dimCol];
		RingElement[][] rowNormal = new RingElement[dimRow][dimRow];
		
		for (int i = 0; i < dimCol; i++)
			for (int j = 0; j < dimCol; j++)
				if (i == j)
					colNormal[i][j] = oneEl;
				else
					colNormal[i][j] = nullEl;

		for (int i = 0; i < dimRow; i++)
			for (int j = 0; j < dimRow; j++)
				if (i == j)
					rowNormal[i][j] = oneEl;
				else
					rowNormal[i][j] = nullEl;
		
		this.colNormalizer = new FiniteMatrix(baseRing,colNormal);
		this.rowNormalizer = new FiniteMatrix(baseRing,rowNormal);
		this.colNormForm = new FiniteMatrix(baseRing,colNormalForm);
		this.rowNormalizer = new FiniteMatrix(baseRing,rowNormalForm);
		
		while (rowPos < dimRow) {

			// find a remaining Vector with map factor not 0
			for (int i = pos; i < dimRow; i++) {
				if (!this.colNormalizer.getElement(pos, i).equals(nullEl)) {
					this.colNormalizer.exchangeColumns(pos, i);
					this.colNormForm.exchangeColumns(pos, i);
					break;
				}
			}
			if (this.matrix.getElement(colPos, pos).equals(this.matrix.getBaseRing().getNullElement())) {
				colPos++;
				continue;
			}

			RingElement factor = matrix.getElement(colPos, pos).getInverse().getNegative();
			// adapt the rest of the vectors and matrix rows to to factor 0 at
			// pos
			for (int i = pos + 1; i < size; i++) {
				this.range.modifyVector(i, pos, matrix.getElement(i, pos - 1).multiply(factor));
				this.matrix.addColumn(i, pos, factor);
			}
			this.range.getBaseVectors()[pos] = (FiniteDimensionalVector) this.range.getBaseVectors()[pos]
					.multiply(matrix.getElement(pos, colPos).getInverse());
			pos++;
			colPos++;
		}
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

	public int getColumnNumber() {
		return this.matrix.length;
	}

	public int getRownNumber() {
		if (this.matrix.length == 0)
			return 0;
		else
			return this.matrix[0].length;
	}

	/*
	 * columns normal diagonal form for Matrix with Rank r: all columns index >
	 * r are 0, top left corner r-dim unit matrix
	 */
	public FiniteMatrix getColumnsDiaginalNormalizedForm() {

		if (this.colNormForm != null)
			return this.colNormForm;


	}

	/*
	 * rows normal diagonal form for Matrix with Rank r: all rows index > r are
	 * 0, top left corner r-dim unit matrix
	 */
	public FiniteMatrix getRowsDiaginalNormalizedForm() {
		return null;
	}

	public void exchangeColumns(int i, int j) {

		RingElement[] coli = new RingElement[matrix.length];
		for (int k = 0; k < matrix.length; k++)
			coli[k] = matrix[k][i];

		for (int k = 0; k < matrix.length; k++)
			matrix[k][i] = matrix[k][j];

		for (int k = 0; k < matrix.length; k++)
			matrix[k][j] = coli[k];
	}

	public void scaleRow(int i, RingElement scalar) {

		for (int k = 0; k < matrix.length; k++)
			matrix[i][k] = scalar.multiply(matrix[i][k]);
	}

	public void scaleColumn(int i, RingElement scalar) {

		for (int k = 0; k < matrix.length; k++)
			matrix[k][i] = scalar.multiply(matrix[k][i]);
	}

	public Matrix getTransposed() {

		RingElement[][] trans = new RingElement[matrix[0].length][matrix.length];

		for (int i = 0; i < matrix.length; i++)
			for (int j = 0; j < matrix[0].length; j++)
				trans[j][i] = matrix[i][j];

		return new FiniteMatrix(this.baseRing, trans);
	}

	public void addRow(int row, int addedRow, RingElement scalar) {

		for (int k = 0; k < matrix[row].length; k++)
			matrix[row][k] = scalar.multiply(matrix[addedRow][k]);
	}

	public void addColumn(int col, int addedCol, RingElement scalar) {

		for (int k = 0; k < matrix.length; k++)
			matrix[k][col] = scalar.multiply(matrix[k][addedCol]);
	}

	public RingElement getElement(int i, int j) {
		return this.matrix[i][j];
	}
}
