package com.compilerWorks.demo;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.compilerWorks.Helpers.Helper;
import com.complierWorks.RuntimeExceptions.InvalidInputData;
import com.complierWorks.RuntimeExceptions.NoSolutionExist;

public class NQueenSolutions {
	private static final Logger logger = LoggerFactory.getLogger(NQueenSolutions.class);

	// default 
	private int gridSize = 0;
	private List<int[][]> lstNqueenSolutions = null;
	/**
	 * Constructor
	 * @param gridSize 
	 * 		  size of the grid for placing Queens 	
	 */
	public NQueenSolutions(int gridSize) {
		if (gridSize <= 0) {
			throw new InvalidInputData();
		}
		this.gridSize = gridSize;
		this.lstNqueenSolutions = new ArrayList<int[][]>();
	}

	/**
	 * Recursive functions to generate grid placement of queen. 
	 * @param grid 
	 * 		  Grid Array  	
	 * @param col
	 * 		  Column for queens position	
	 * @return
	 */
	boolean backTrackUtil(int grid[][], int col) {
		/*
		 * base case: If all queens are placed then return true and add to list of the
		 * all solutions
		 */
		if (col == gridSize) {
			// clone the solution grid and add to list 
			int grid_clone[][] = new int[gridSize][gridSize];
			for (int i = 0; i < grid.length; i++)
				for (int j = 0; j < grid[i].length; j++)
					grid_clone[i][j] = grid[i][j];
			this.lstNqueenSolutions.add(grid_clone);
			return true;
		}

		/*
		 * Consider this column and try placing this queen in all rows one by one
		 */
		boolean res = false;
		for (int i = 0; i < gridSize; i++) {
			/*
			 * Check if queen can be placed on grid[i][col]
			 */
			if (Helper.isQueenSafe(grid, i, col, gridSize)) {
				/* Place this queen in grid[i][col] */
				grid[i][col] = 1;

				// Make result true if any placement on the row
				// is possible
				res = backTrackUtil(grid, col + 1) || res;

				/*
				 * If placing queen in grid[i][col] doesn't lead to a solution, then remove
				 * queen from grid[i][col]
				 */
				grid[i][col] = 0; // BACKTRACK
			}
		}

		/*
		 * If queen can not be place in any row in this column col then return false
		 */
		// System.out.println("return false");
		return res;
	}

	/**
	 * @return 
	 * 		 List of Solutions grid with queen placed on the grid.
	 * @throws NoSolutionExist
	 */
	public List<int[][]> solve() throws NoSolutionExist {
		int grid[][] = new int[gridSize][gridSize];
		// start with column 0
		if (backTrackUtil(grid, 0) == false) {
			// No solutions exists.
			throw new NoSolutionExist();
		}
		logger.info(" Number of solutions  with no Filter " + this.lstNqueenSolutions.size());

		return this.lstNqueenSolutions;
	}

}
