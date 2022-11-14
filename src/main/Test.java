package main;

import controller.Sudoku;
import figures.Data;

public class Test {
	public static void main(String[] args) {
		int[][] individual = { { 0, 0, 4, 0, 5, 0, 0, 6, 0 }, { 0, 6, 0, 1, 0, 0, 8, 0, 9 },
				{ 3, 0, 0, 0, 0, 7, 0, 0, 0 }, { 0, 8, 0, 0, 0, 0, 5, 0, 0 }, { 0, 0, 0, 4, 0, 3, 0, 0, 0 },
				{ 0, 0, 6, 0, 0, 0, 0, 7, 0 }, { 0, 0, 0, 2, 0, 0, 0, 0, 6 }, { 1, 0, 5, 0, 0, 4, 0, 3, 0 },
				{ 0, 2, 0, 0, 7, 0, 1, 0, 0 } };

		Sudoku sudoku = new Sudoku();
		sudoku.initPop(individual);
		sudoku.selection();

		for (int i = 0; i < Data.POP_SIZE - 1; i += 2) {
			for (int j = i + 1; j < i + 2; j++) {
				if (j == sudoku.getPopulation().getListInd().size() - 1 && j % 2 != 0) {
					sudoku.crossover(sudoku.getPopulation().getListInd().get(i),
							sudoku.getPopulation().getListInd().get(j));
				}
			}
		}
		System.out.println(sudoku.getPopulation().getListInd().size());
		for (int k = 0; k < sudoku.getPopulation().getListInd().size(); k++) {
			for (int row = 0; row < Data.GEN_SIZE; row++) {
				for (int column = 0; column < Data.GEN_SIZE; column++) {
					System.out.print(sudoku.getPopulation().getListInd().get(k).getIndividual()[row][column] + ", ");
				}
				System.out.println("\n");
			}
			System.out.println("___________________________________");

		}
	}
}
