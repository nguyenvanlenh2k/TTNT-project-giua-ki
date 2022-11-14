package model;

import java.util.ArrayList;
import java.util.Random;

import figures.Data;

// 9x9 là 1 cá thể
public class Individual {
	int[][] individual = new int[Data.GEN_SIZE][Data.GEN_SIZE];
	boolean[][] geneCheck = new boolean[Data.GEN_SIZE][Data.GEN_SIZE];
//	ArrayList<ArrayList<Integer>> initial = new ArrayList<ArrayList<Integer>>(individual.length);

	public Individual(int[][] individual) {
		setIndividual(individual);
		checkGen();
		init();
	}

	public void init() {
		int numTest;
		Random rd = new Random();
		for (int i = 0; i < Data.GEN_SIZE; i++) {
			for (int j = 0; j < Data.GEN_SIZE; j++) {
				if (individual[i][j] == 0) {
					numTest = rd.nextInt(Data.GEN_SIZE) + 1;
					if (notExists(numTest, i)) {
						individual[i][j] = numTest;
					}
				}
			}
			for (int k = 0; k < Data.GEN_SIZE; k++) {
				if (individual[i][k] == 0) {
					init();
				}
			}
		}
	}

	private boolean notExists(int numTest, int i) {
		boolean result = true;
		for (int j = 0; j < Data.GEN_SIZE; j++) {
			if (individual[i][j] == numTest)
				result = false;
		}
		return result;
	}

	public void checkGen() {
		for (int i = 0; i < Data.GEN_SIZE; i++) {
			for (int j = 0; j < Data.GEN_SIZE; j++) {
				if (individual[i][j] != 0) {
					geneCheck[i][j] = true;
				}
			}
		}
	}

	public int heuristic(Individual ind) {
		return column(ind)  + cluster3x3(ind);

	}

	private static int column(Individual in) {
		int count = 0;
		int[][] arr = in.getIndividual();
		int size = Data.GEN_SIZE;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				for (int k = j + 1; k < size; k++) {
					if (arr[i][j] == arr[i][k] && j != k)
						count++;
				}
			}
		}
		return count;
	}


	public static int cluster3x3(Individual in) {
		int[][] arr = in.getIndividual();
		int result = 0;
		for (int m = 0; m < Data.GEN_SIZE; m += 3) {
			for (int n = 0; n < Data.GEN_SIZE; n += 3) {
				result += clusterSum(arr, m, n);
			}
		}
		return result;
	}

	// Chia mảng thành 9 cụm
	public static int clusterSum(int[][] arr, int a, int b) {
		int count = 0;
		for (int i = a; i < a + 3; i++) {
			for (int j = b; j < b + 3; j++) {
//				lấy từng vị trí để so sánh với từng vị trí khác trong mảng
				for (int k = a; k < a + 3; k++) {
					for (int l = b; l < b + 3; l++) {
//						arr[k][l] là vị trí được lấy ra để so sánh với từng arr[i][j]
						if (arr[k][l] == arr[i][j])
							count++;
					}
				}
			}
		}
//		count-9 là trừ bớt so sánh với chính nó
		return count - 9;
	}

	public Individual() {
	}

	public void setIndividual(int[][] individual) {
		this.individual = individual;
	}

	public int[][] getIndividual() {
		return individual;
	}

	public int[] getIndividualLine(int i) {
		return individual[i];
	}

	public boolean[][] getGeneCheck() {
		return geneCheck;
	}

	public static void main(String[] args) {
		Individual i;

		for (int k = 0; k < 2; k++) {
			int[][] individual = { { 0, 0, 4, 0, 5, 0, 0, 6, 0 }, { 0, 6, 0, 1, 0, 0, 8, 0, 9 },
					{ 3, 0, 0, 0, 0, 7, 0, 0, 0 }, { 0, 8, 0, 0, 0, 0, 5, 0, 0 }, { 0, 0, 0, 4, 0, 3, 0, 0, 0 },
					{ 0, 0, 6, 0, 0, 0, 0, 7, 0 }, { 0, 0, 0, 2, 0, 0, 0, 0, 6 }, { 1, 0, 5, 0, 0, 4, 0, 3, 0 },
					{ 0, 2, 0, 0, 7, 0, 1, 0, 0 } };
			i = new Individual(individual);
			i.init();

			for (int row = 0; row < Data.GEN_SIZE; row++) {
				for (int column = 0; column < Data.GEN_SIZE; column++) {
					System.out.print(i.individual[row][column] + ", ");
					System.out.print(i.geneCheck[row][column] + ", ");
				}
				System.out.println("\n");
			}
			System.out.println("___________________________________");
			System.out.println((new Individual()).heuristic(i));
		}


	}

}
//	public void array2DToList(int[][] arr) {
//		for (int row = 0; row < Data.GEN_SIZE; row++) {
//			for (int column = 0; column < Data.GEN_SIZE; column++) {
//				initial.get(row).set(column, arr[row][column]);
//			}
//		}
//	}
//
//	public void init2() {
//		int numTest;
//		Random rd = new Random();
//		for (int i = 0; i < Data.GEN_SIZE; i++) {
//			for (int j = 0; j < Data.GEN_SIZE; j++) {
//				if (initial.get(i).get(j) == 0) {
//					numTest = rd.nextInt(Data.GEN_SIZE) + 1;
//					if (notExists2(numTest, i)) {
//						initial.get(i).set(j, numTest);
//					}
//				}
//			}
//			for (int k = 0; k < Data.GEN_SIZE; k++) {
//				if (initial.get(i).get(k) == 0)
//					init2();
//			}
//		}
//	}
//
//	private boolean notExists2(int numTest, int i) {
//		boolean result = true;
//		for (int j = 0; j < Data.GEN_SIZE; j++) {
//			if (initial.get(i).get(j) == numTest)
//				result = false;
//		}
//		return result;
//	}