package model;

import java.util.ArrayList;
import java.util.List;

import figures.Data;

public class Population {

	List<Individual> listInd;

//	public Population(int[][] arr) {
//		listInd = new ArrayList<>();
//		for (int i = 0; i < Data.POP_SIZE; i++) {
//			Individual in = new Individual(initArr(arr));
//			listInd.add(in);
//		}
//	}
	

	public Population() {
		listInd = new ArrayList<>();
	}
	
	public void initPop(int[][] arr) {
		for (int i = 0; i < Data.POP_SIZE; i++) {
			Individual in = new Individual(initArr(arr));
			listInd.add(in);
		}
	}
	private int[][] initArr(int[][] arr) {
		int[][] initArr = new int[Data.GEN_SIZE][Data.GEN_SIZE];
		for (int row = 0; row < Data.GEN_SIZE; row++) {
			for (int column = 0; column < Data.GEN_SIZE; column++) {
				initArr[row][column] = arr[row][column];
			}
		}
		return initArr;
	}


	public List<Individual> getListInd() {
		return listInd;
	}

	public void setPopulation(List<Individual> population) {
		this.listInd = population;
	}

	public static void main(String[] args) {

		int[][] individual = { 
				{ 0, 0, 4, 0, 5, 0, 0, 6, 0 },
				{ 0, 6, 0, 1, 0, 0, 8, 0, 9 },
				{ 3, 0, 0, 0, 0, 7, 0, 0, 0 },
				{ 0, 8, 0, 0, 0, 0, 5, 0, 0 }, 
				{ 0, 0, 0, 4, 0, 3, 0, 0, 0 },
				{ 0, 0, 6, 0, 0, 0, 0, 7, 0 },
				{ 0, 0, 0, 2, 0, 0, 0, 0, 6 },
				{ 1, 0, 5, 0, 0, 4, 0, 3, 0 },
				{ 0, 2, 0, 0, 7, 0, 1, 0, 0 } };
		Population population = new Population();
		population.initPop(individual);
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < Data.GEN_SIZE; j++) {
				for (int k = 0; k < Data.GEN_SIZE; k++) {
					System.out.print(population.listInd.get(i).individual[j][k]);
				}
				System.out.println();
			}
			System.out.println("_____________________________________");
		}

	}

	
}
