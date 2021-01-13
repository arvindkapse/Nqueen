package com.compilerWorks.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Test;

import com.comilerWorks.Nqueen.filters.StraightLineFilter;
import com.complierWorks.RuntimeExceptions.InvalidInputData;
import com.complierWorks.RuntimeExceptions.NoSolutionExist;

public class NqeenTests {

	// Negative grid test
	@Test(expected = InvalidInputData.class)
	public void testNqueensNegative() {

		int gridSize = -5;
		NQueenSolutions nq = new NQueenSolutions(gridSize);
		List<int[][]> lstAllsolutionsgrid = nq.solve();
		assertEquals(2, lstAllsolutionsgrid.size());

	}

	// 2 * 2 grid size
	@Test(expected = NoSolutionExist.class)
	public void testNqueens2() {

		int gridSize = 2;
		NQueenSolutions nq = new NQueenSolutions(gridSize);
		List<int[][]> lstAllsolutionsgrid = nq.solve();
		assertEquals(2, lstAllsolutionsgrid.size());

	}

	// 4 * 4 grid size
	@Test
	public void testNqueens4GridFilter() {
		// no of solutions for 4 grids
		int gridSize = 4;
		NQueenSolutions nq = new NQueenSolutions(gridSize);
		List<int[][]> lstAllsolutionsgrid = nq.solve();
		assertEquals(2, lstAllsolutionsgrid.size());

		List<int[][]> lstFiltered = StraightLineFilter.applyStraightLineeFilter(lstAllsolutionsgrid, gridSize, 3);
		assertEquals(2, lstFiltered.size());

	}

	@Test(expected = InvalidInputData.class)
	public void testNqueensInvalidFilter() {
		// no of solutions for 4 grids
		int gridSize = 4;
		NQueenSolutions nq = new NQueenSolutions(gridSize);
		List<int[][]> lstAllsolutionsgrid = nq.solve();
		assertEquals(2, lstAllsolutionsgrid.size());

		List<int[][]> lstFiltered = StraightLineFilter.applyStraightLineeFilter(lstAllsolutionsgrid, gridSize, -4);
		assertEquals(2, lstFiltered.size());

	}

	// 5 * 5 grid size and slope points = 3
	@Test
	public void testNqueens5GridFilter() {
		// no of solutions for 4 grids
		int gridSize = 5;
		NQueenSolutions nq = new NQueenSolutions(gridSize);
		List<int[][]> lstAllsolutionsgrid = nq.solve();
		assertEquals(10, lstAllsolutionsgrid.size());

		List<int[][]> lstFiltered = StraightLineFilter.applyStraightLineeFilter(lstAllsolutionsgrid, gridSize, 2);
		assertEquals(2, lstFiltered.size());

	}

	// 6 * 6 grid size and slope points = 2
	@Test
	public void testNqueens6GridFilter() {
		// no of solutions for 4 grids
		int gridSize = 6;
		NQueenSolutions nq = new NQueenSolutions(gridSize);
		List<int[][]> lstAllsolutionsgrid = nq.solve();
		assertEquals(4, lstAllsolutionsgrid.size());

		List<int[][]> lstFiltered = StraightLineFilter.applyStraightLineeFilter(lstAllsolutionsgrid, gridSize, 2);
		assertEquals(0, lstFiltered.size());

	}

	// 10 * 10 grid size and slope points = 2
	@Test
	public void testNqueens10GridFilter() {
		// no of solutions for 4 grids
		int gridSize = 10;
		NQueenSolutions nq = new NQueenSolutions(gridSize);
		List<int[][]> lstAllsolutionsgrid = nq.solve();
		assertEquals(724, lstAllsolutionsgrid.size());

		List<int[][]> lstFiltered = StraightLineFilter.applyStraightLineeFilter(lstAllsolutionsgrid, gridSize, 2);
		assertEquals(212, lstFiltered.size());

	}
}
