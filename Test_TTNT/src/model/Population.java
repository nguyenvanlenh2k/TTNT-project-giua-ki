package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import figures.Data;

public class Population {

	List<Individual> listInd;

	public Population() {
		listInd = new ArrayList<>();
	}
	// khoi tao quan the
	public void initPop(int[][] arr) {
		Individual in;
		for (int i = 0; i < Data.POP_SIZE; i++) {
			in = new Individual(initArr(arr));
			// khoi tao ca the
			in.init();
			listInd.add(in);
		}
	}
	// khoi tao mang
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

	// show các cá thể
	public void displayPopu() {
		for (Individual in : getListInd()) {
			System.out.println(in.heuristic());
			System.out.println(in);
		}
	}

	// chon lai 100 ca the tot
	public void selectIndividual() {
		setPopulation(getListInd().subList(0, Data.POP_SIZE));
	}

	public static void main(String[] args) {

		int[][] individual = { { 0, 0, 4, 0, 5, 0, 0, 6, 0 }, { 0, 6, 0, 1, 0, 0, 8, 0, 9 },
				{ 3, 0, 0, 0, 0, 7, 0, 0, 0 }, { 0, 8, 0, 0, 0, 0, 5, 0, 0 }, { 0, 0, 0, 4, 0, 3, 0, 0, 0 },
				{ 0, 0, 6, 0, 0, 0, 0, 7, 0 }, { 0, 0, 0, 2, 0, 0, 0, 0, 6 }, { 1, 0, 5, 0, 0, 4, 0, 3, 0 },
				{ 0, 2, 0, 0, 7, 0, 1, 0, 0 } };
		Population population = new Population();
		population.initPop(individual);
//		population.displayPopu();
	}

}
