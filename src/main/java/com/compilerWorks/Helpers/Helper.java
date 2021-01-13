package com.compilerWorks.Helpers;

public class Helper {
	/**
	 * Function to validate if a Queen can be placed at (row,col) in a given grid.
	 * 
	 * @param grid
	 * @param row
	 * @param col
	 * @param gridSize
	 * @return
	 */
	public static boolean isQueenSafe(int grid[][], int row, int col, int gridSize) {
		int i, j;

		/* Check on the row both sides */
		for (i = 0; i < col; i++)
			if (grid[row][i] == 1)
				return false;

		for (i = col; i < gridSize; i++)
			if (grid[row][i] == 1)
				return false;

		/* Check diagonal left */
		for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
			if (grid[i][j] == 1)
				return false;

		/* Arvind diagonal right */
		for (i = row, j = col; i >= 0 && j >= gridSize; i--, j++)
			if (grid[i][j] == 1)
				return false;

		/* Check lower diagonal */
		for (i = row, j = col; j >= 0 && i < gridSize; i++, j--)
			if (grid[i][j] == 1)
				return false;

		for (i = row, j = col; j >= gridSize && i < gridSize; i++, j++)
			if (grid[i][j] == 1)
				return false;

		return true;
	}

	/**
	 * Print the grid out to system console
	 * 
	 * @param grid
	 * @param gridCount
	 * @param gridSize
	 */
	public static void printGrid(int grid[][], int gridCount, int gridSize) {
		System.out.printf("Solutions %d-\n", gridCount);
		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++)
				System.out.printf(" %d ", grid[i][j]);
			System.out.printf("\n");
		}
		System.out.printf("\n");
	}
}
