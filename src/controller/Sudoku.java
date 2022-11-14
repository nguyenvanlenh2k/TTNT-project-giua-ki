package controller;

import java.util.Random;

import figures.Data;
import model.Individual;
import model.Population;

public class Sudoku {
	Population population;

//	public Sudoku(int[][] initIndividual) {
//		population = new Population();
//	}
	public void initPop(int[][] initIndividual) {
		population.initPop(initIndividual);
	}
	
	public Population getPopulation() {
		return population;
	}

	public Sudoku() {
		population = new Population();
	}

	// lai CÁC SOLUTTION ( CÁ THỂ) 3x3 or các dòng cột ( cắt)
	public void crossover(Individual dad, Individual mom) {
		// khởi tạo 2 con
		int[][] child1 = new int[Data.GEN_SIZE][Data.GEN_SIZE];
		int[][] child2 = new int[Data.GEN_SIZE][Data.GEN_SIZE];
		split(child1, child2, dad, mom);
	}

	// cắt theo index chỉ định trước
	private void split(int[][] child1, int[][] child2, Individual dad, Individual mom) {
		for (int i = 0; i < Data.GEN_SIZE; i++) {
			for (int j = 0; j < Data.GEN_SIZE; j++) {
				if (i < Data.INDEX_SPLIT) {
					child1[i][j] = dad.getIndividual()[i][j];
					child2[i][j] = mom.getIndividual()[i][j];
				} else {
					child2[i][j] = dad.getIndividual()[i][j];
					child1[i][j] = mom.getIndividual()[i][j];
				}
			}
		}
		// add vào quần thể
		population.getListInd().add(new Individual(child1));
		population.getListInd().add(new Individual(child2));
	}

	// đột biến trên 1 dòng bất kì
	public Individual mutation(Individual ind) {
		Individual result = ind;
		Random rd = new Random();
		int point1 = rd.nextInt(Data.GEN_SIZE);
		int point2 = rd.nextInt(Data.GEN_SIZE);
		for (int i = 0; i < Data.LINES_MUTA; i++) {
			int index = rd.nextInt(Data.GEN_SIZE);
			int[] line = result.getIndividualLine(index);
			if (result.getGeneCheck()[index][point1] == false && result.getGeneCheck()[index][point2] == false)
				swap(line, point1, point2);
		}

		return result;
	}

	private void swap(int[] arr, int point1, int point2) {
		int temp = 0;
		if (point1 != point2) {
			temp = arr[point1];
			arr[point1] = arr[point2];
			arr[point2] = temp;
		}
	}

	// chọn lọc
	public void selection() {
		
	}

}
