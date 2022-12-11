package main;

import java.util.List;

import controller.Sudoku;
import figures.Data;
import model.CheckInstance;
import model.Individual;
import view.SudokuFrame2;

public class Test {
	public static void main(String[] args) {
		int[][] kho = {  { 0, 0, 4, 0, 0, 0, 0, 6, 0 }, 
								{ 0, 0, 0, 1, 0, 0, 8, 0, 9 },
								{ 3, 0, 0, 0, 0, 7, 0, 0, 0 }, 
								{ 0, 8, 0, 0, 0, 0, 5, 0, 0 }, 
								{ 0, 0, 0, 4, 0, 0, 0, 0, 0 },
								{ 0, 0, 0, 0, 0, 0, 0, 7, 0 }, 
								{ 0, 0, 0, 2, 0, 0, 0, 0, 6 }, 
								{ 1, 0, 0, 0, 0, 4, 0, 3, 0 },
								{ 0, 2, 0, 0, 7, 0, 1, 0, 0 } };
		int[][] kho1 = {  { 8, 1, 4, 2, 9, 5, 7, 6, 3 }, 
							{ 7, 5, 2, 1, 6, 3, 8, 4, 9 },
							{ 3, 6, 9, 8, 4, 7, 2, 5, 1 }, 
							{ 7, 8, 9, 2, 6, 1, 5, 3, 4 }, 
							{ 3, 5, 1, 4, 9, 7, 8, 2, 6 },
							{ 2, 6, 4, 3,5,8, 9, 7, 1 }, 
							{ 8, 3, 9, 2, 1, 5, 7, 4, 6 }, 
							{ 1, 5, 7, 9, 6, 4, 2, 3, 8 },
							{ 6, 2, 4, 8, 7, 3, 1, 5, 9 } };
		int[][] trungbinh ={{ 5, 3, 0, 0, 7, 0, 0, 0, 0 }, 
							{ 6, 0, 0, 1, 9, 5, 0, 0, 0 },
							{ 0, 9, 8, 0, 0, 0, 0, 6, 0 }, 
							{ 8, 0, 0, 0, 6, 0, 0, 0, 3 }, 
							{ 4, 0, 0, 8, 0, 3, 0, 0, 1 },
							{ 7, 0, 0, 0, 2, 0, 0, 0, 6 }, 
							{ 0, 6, 0, 0, 0, 0, 2, 8, 0 }, 
							{ 0, 0, 0, 4, 1, 9, 0, 0, 5 },
							{ 0, 0, 0, 0, 8, 0, 0, 7, 9 }};
		int[][] de ={
					{ 0, 0, 7, 8, 0, 0, 9, 0, 0 }, 
					{ 0, 0, 0, 5, 0, 0, 0, 3, 1 }, 
					{ 9, 0, 0, 0, 0, 1, 0, 4, 0 }, 
					{ 2, 1, 0, 0, 6, 0, 7, 8, 0 }, 
					{ 0, 0, 0, 0, 0, 3, 0, 9, 0 }, 
					{ 3, 0, 9, 0, 1, 0, 2, 0, 0 }, 
					{ 4, 0, 0, 0, 0, 0, 0, 1, 6 }, 
					{ 0, 0, 0, 1, 0, 9, 0, 0, 8 }, 
					{ 0, 8, 0, 0, 3, 0, 0, 0, 0 }}; 
					
		

		SudokuFrame2 frame = new SudokuFrame2();
		for (int row = 0; row < Data.GEN_SIZE; row++) {
			for (int column = 0; column < Data.GEN_SIZE; column++) {
				if(kho[row][column] != 0) {
					frame.bt[row][column].setText(kho[row][column]+"");
				}
			}
		}
		Sudoku sudoku = new Sudoku();
		sudoku.initPop(kho);
		System.out.println(sudoku.genetic());
		
		for (int row = 0; row < Data.GEN_SIZE; row++) {
			for (int column = 0; column < Data.GEN_SIZE; column++) {
				if(sudoku.result[row][column] != 0) {
					frame.bt[row][column].setText(sudoku.result[row][column]+"");
				}
			}
		}
		
	}
}
