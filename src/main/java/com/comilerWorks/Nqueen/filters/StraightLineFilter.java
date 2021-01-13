package com.comilerWorks.Nqueen.filters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.complierWorks.RuntimeExceptions.InvalidInputData;

public class StraightLineFilter {
	private static final Logger logger = LoggerFactory.getLogger(StraightLineFilter.class);

	/**
	 * * A utility function to generate a list of slope value between position
	 * [row,column] and all other pieces placed on the board.
	 * 
	 * @param board
	 *            the grid array
	 * @param row
	 *            row value of the reference piece placed on the grid
	 * @param col
	 *            col value of the reference piece placed on the grid
	 * @param gridSize
	 *            board size
	 * @return returns a list of slopes against all pieces on the board with
	 *         reference to [row,col]
	 */

	static double[] calculateSlopes(int board[][], int row, int col, int gridSize) {
		int columnIndex, rowIndex;
		double lstSlopes[] = new double[gridSize - 1];
		int k = 0;
		for (columnIndex = 0; columnIndex < gridSize; columnIndex++) {
			for (rowIndex = 0; rowIndex < gridSize; rowIndex++) {
				if (rowIndex != row && columnIndex != col) { // default to 0.0 for slope with itself
					if (board[rowIndex][columnIndex] == 1) { // piece found
						// calculate slope = (x2-x1/y2-y1)
						int x = Math.abs(columnIndex - col);
						int y = Math.abs(rowIndex - row);
						double slope = (double) x / (double) y;
						lstSlopes[k] = slope;
						k++;

					}
				}
			}
		}
		return lstSlopes;

	}

	/**
	 * A utility function to check the no of points(slopes) that fall in the
	 * straight line minMatch == minimum no of points on the line for the function
	 * to return true.
	 * 
	 * @param lstSlopes
	 *            a double array of slopes
	 * @param minMatch
	 *            A minimum no of frequency to match
	 * @return true if the frequency of any slope value is equal to greater that
	 *         minMatch else false
	 */
	static boolean isStraightLine(double lstSlopes[], int minMatch) {
		Double[] doubleArray = ArrayUtils.toObject(lstSlopes);
		List<Double> asList = Arrays.asList(doubleArray);
		for (Double s : lstSlopes) {
			if (Collections.frequency(asList, s) >= minMatch) {
				return true;
			}
		}
		return false;
	}

	/**
	 * A function to Apply slope filter on NqueenSolutions grids and return only
	 * those grids in which no (minMatch) no of queens are in straight line.
	 * 
	 * @param lstgrid
	 *            A list of grid on which to Apply straight line filter
	 * @param gridSize
	 *            Grid size
	 * @param minMatch
	 *            Minimum of no of pieces that should be in line
	 * @return
	 */
	public static List<int[][]> applyStraightLineeFilter(List<int[][]> lstgrid, int gridSize, int minMatch) {
		if (gridSize < 0) {
			throw new InvalidInputData();
		}
		if (minMatch < 0) {
			throw new InvalidInputData();
		}
		List<int[][]> lstFilteredGrid = new ArrayList<int[][]>();

		for (int[][] board : lstgrid) {
			boolean matchesFilter = false;
			for (int row = 0; row < gridSize; row++) {
				for (int col = 0; col < gridSize; col++)

					if (board[row][col] == 1) { // refrence queen
						double lstSlopes[] = calculateSlopes(board, row, col, gridSize);
						boolean pointOnStraightLine = isStraightLine(lstSlopes, minMatch);
						if (pointOnStraightLine) {
							matchesFilter = false;

							// break;
						} else {
							matchesFilter = true;
						}

					}

			}
			if (matchesFilter) {
				lstFilteredGrid.add(board);
			}
		}
		logger.info("Number of Solutions with  Straight line Filter " + lstFilteredGrid.size());
		return lstFilteredGrid;
	}

}
