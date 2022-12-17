package controller;

import java.util.Collections;
import java.util.Random;

import figures.Data;
import model.CheckInstance;
import model.Individual;
import model.Population;

public class Sudoku {
	Population population;
	boolean[][] geneCheck = new boolean[Data.GEN_SIZE][Data.GEN_SIZE];
	public int[][] result = new int[Data.GEN_SIZE][Data.GEN_SIZE];

	public void initPop(int[][] initIndividual) {
		geneCheck = CheckInstance.getInstance().checkGen(initIndividual);
		population.initPop(initIndividual);
	}

	public Population getPopulation() {
		return population;
	}

	public Sudoku() {
		population = new Population();
	}

	// lai
	public void crossover(Individual dad, Individual mom) {
		// khoi tao 2 con
		int[][] child1 = new int[Data.GEN_SIZE][Data.GEN_SIZE];
		int[][] child2 = new int[Data.GEN_SIZE][Data.GEN_SIZE];
		split(child1, child2, dad, mom);
	}

	// cat vi tri chi dinh
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
		// add vao quan the
		population.getListInd().add(new Individual(child1));
		population.getListInd().add(new Individual(child2));
	}

	// dot bien dong bat ki
	public Individual mutation(Individual ind) {
		Individual result = ind;
		int index, point1, point2;
		int[] line;
		Random rd = new Random();
		for (int i = 0; i < Data.LINES_MUTA; i++) {
			// random 2 vi tri bat ki va dong
			point1 = rd.nextInt(Data.GEN_SIZE);
			point2 = rd.nextInt(Data.GEN_SIZE);
			index = rd.nextInt(Data.GEN_SIZE);
			// lay 1 dong
			line = result.getIndividualLine(index);
			if (geneCheck[index][point1] == false && geneCheck[index][point2] == false)
				swap(line, point1, point2);
		}
		return result;
	}

	// dot bien ( doi cho 2 vi tri )
	private void swap(int[] arr, int point1, int point2) {
		int temp = 0;
		if (point1 != point2) {
			temp = arr[point1];
			arr[point1] = arr[point2];
			arr[point2] = temp;
		}
	}

	public Individual genetic() {
		// sắp xếp quần thể theo heuristic
		Collections.sort(getPopulation().getListInd());
		// lấy 100 cá thể đầu
		population.selectIndividual();
		// lấy ra cá thể đầu tiên
		// kiểm tra xem nó tối ưu chưa
		Individual goal = getPopulation().getListInd().get(0);
		if (goal.heuristic() != 0) {
			System.out.println("Đang chạy..., Goal: " + goal.heuristic());
		}
		if (goal.heuristic() == 0) {
			result = goal.getIndividual();
			System.out.println("Đã xong, Goal: " + goal.heuristic());
			return goal;
		}

		// cho lai với nhau
		for (int i = 0; i < Data.POP_SIZE; i += 2) {
			for (int j = i + 1; j < i + 2; j++) {
				
				if (j % 2 != 0) {// phòng nếu quần thể size lẻ
					crossover(getPopulation().getListInd().get(i), getPopulation().getListInd().get(j));
				}
			}
		}

		// cho đột biến 20 đứa con sau
		int size = getPopulation().getListInd().size();

		Individual dotbien;
		for (int i = size - Data.SLDOTBIEN; i < size; i++) {
			dotbien = mutation(getPopulation().getListInd().get(i));
			// add vào quần thể
			population.getListInd().add(dotbien);

		}
		return genetic();
	}

	public static void main(String[] args) {
		int[][] individual = { { 0, 0, 4, 0, 5, 0, 0, 6, 0 }, { 0, 6, 0, 1, 0, 0, 8, 0, 9 },
				{ 3, 0, 0, 0, 0, 7, 0, 0, 0 }, { 0, 8, 0, 0, 0, 0, 5, 0, 0 }, { 0, 0, 0, 4, 0, 3, 0, 0, 0 },
				{ 0, 0, 6, 0, 0, 0, 0, 7, 0 }, { 0, 0, 0, 2, 0, 0, 0, 0, 6 }, { 1, 0, 5, 0, 0, 4, 0, 3, 0 },
				{ 0, 2, 0, 0, 7, 0, 1, 0, 0 } };
		Sudoku sudoku = new Sudoku();
		sudoku.initPop(individual);
		sudoku.population.displayPopu();
	}
}
