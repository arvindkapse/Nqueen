package com.compilerWorks.demo;

import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.comilerWorks.Nqueen.filters.StraightLineFilter;
import com.compilerWorks.Helpers.Helper;
import com.complierWorks.RuntimeExceptions.InvalidInputData;

/**
 * @author arvind
 *
 */
public class NQueenApp {
	private static final Logger logger = LoggerFactory.getLogger(NQueenApp.class);

	/**
	 * Entry for Nqueens problem with straight line Filter
	 * @param args (n = grid size , q = minimum number of queens in straight line)
	 */
	public static void main(String[] args) {

		try {
			Options options = new Options();

			Option gridSize = new Option("n", "grid", true, "grid size ");
			gridSize.setRequired(true);
			options.addOption(gridSize);

			Option noOfQueens = new Option("q", "noofqueens", true, "No of queens in straight line");
			noOfQueens.setRequired(true);
			options.addOption(noOfQueens);

			CommandLineParser parser = new DefaultParser();
			HelpFormatter formatter = new HelpFormatter();
			CommandLine cmd;

			try {
				cmd = parser.parse(options, args);
			} catch (ParseException e) {
				System.out.println(e.getMessage());
				formatter.printHelp("com.compilerWorks.demo.NQueenApp -n 5 -q 3 ", options);
				System.exit(1);
				return;
			}
			int grid = Integer.parseInt(cmd.getOptionValue("grid"));
			int queens = Integer.parseInt(cmd.getOptionValue("noofqueens"));
			// int grid =10;
			// int queens = -10;

			logger.info("User Input - Grid Size  " + grid);
			logger.info("User Input - No of queens on straight line " + queens);
			// slope  with other 2 for three queens to be on straight line
			queens = queens - 1;
			NQueenSolutions nq = new NQueenSolutions(grid);
			List<int[][]> lstAllsolutionsgrid = nq.solve();
			if (lstAllsolutionsgrid == null) {
				logger.info("Number Solutions for the grid without applying filter");
			}

			if (lstAllsolutionsgrid != null && lstAllsolutionsgrid.size() > 0) {
				// Apply straightline filter to the generic grid solutions list for nqueen
				// problem
				List<int[][]> lstFiltered = StraightLineFilter.applyStraightLineeFilter(lstAllsolutionsgrid, grid,
						queens);

				if (lstFiltered.size() == 0) {
					logger.info("No grid solutions for given input data");
				}

				int displayCount = 1;
				for (int[][] solvedGrid : lstFiltered) {
					Helper.printGrid(solvedGrid, displayCount, grid);
					displayCount++;
				}
			} else {
				logger.info("No grid solutions for given input data");
			}
		}catch(InvalidInputData id){
			logger.error("Invalid input grid size");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Contact system admin");
		}

		// System.out.println("working");
	}

	// assumption out put on console

}
