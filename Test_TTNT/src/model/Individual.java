package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import figures.Data;

// 9x9 là 1 cá thể
public class Individual implements Comparable<Individual> {
	int[][] individual = new int[Data.GEN_SIZE][Data.GEN_SIZE];

	public Individual(int[][] individual) {
		this.individual = individual;
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

	// Tính số xung đột
	public int heuristic() {

		return cluster()  + colum();
	}

//	Tính xung đột theo cụm 3x3
	public int cluster() {
		int result = 0;
		for (int m = 0; m < Data.GEN_SIZE; m += 3) {
			for (int n = 0; n < Data.GEN_SIZE; n += 3) {
				result += clusterSum(m, n);
			}
		}
		return result;
	}

//	Chia mảng thành 9 cụm 
	public int clusterSum(int a, int b) {
		int count = 0;
		int[][] arr = individual;
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

	private int colum() {
		int count = 0;
		int[][] arr = individual;
		int size = Data.GEN_SIZE;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				for (int k = j + 1; k < size; k++) {
					if (arr[j][i] == arr[k][i])
						count++;
				}
			}
		}
		return count;
	}

	public Individual() {
	}

	public void setIndividual(int[][] individual) {
		this.individual = individual;
	}

	public int[][] getIndividual() {
		return individual;
	}

	// lay 1 dong trong ca the
	public int[] getIndividualLine(int i) {
		return individual[i];
	}

	@Override
	public int compareTo(Individual indivaidual) {
		// TODO Auto-generated method stub
		int heuric = ((Individual) indivaidual).heuristic();
		return this.heuristic() - heuric;
	}

	@Override
	public String toString() {
		String result = "";
		for (int i = 0; i < Data.GEN_SIZE; i++) {
			for (int j = 0; j < Data.GEN_SIZE; j++) {
				result += individual[i][j] + " ";
			}
			result += "\n";
		}
		return result;
	}
	
	public static void main(String[] args) {
		int[][] kho1 = {  	{ 8, 1, 4, 2, 9, 5, 7, 6, 3 }, 
							{ 7, 5, 2, 1, 6, 3, 8, 4, 9 },
							{ 3, 6, 9, 8, 4, 7, 2, 5, 1 }, 
							{ 7, 8, 9, 2, 6, 1, 5, 3, 4 }, 
							{ 3, 5, 1, 4, 9, 7, 8, 2, 6 },
							{ 2, 6, 4, 3, 5, 8, 9, 7, 1 }, 
							{ 8, 3, 9, 2, 1, 5, 7, 4, 6 }, 
							{ 1, 5, 7, 9, 6, 4, 2, 3, 8 },
							{ 6, 2, 4, 8, 7, 3, 1, 5, 9 } };
		
	
	}
}
