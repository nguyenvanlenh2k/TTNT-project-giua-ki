package model;

import figures.Data;

public class CheckInstance {

	boolean[][] geneCheck = new boolean[Data.GEN_SIZE][Data.GEN_SIZE];
	private static CheckInstance instance;

	private CheckInstance() {
	}

	public static CheckInstance getInstance() {
		if (instance == null) {
			instance = new CheckInstance();
		}
		return instance;
	}

	public boolean[][] checkGen(int[][] individual) {
		for (int i = 0; i < Data.GEN_SIZE; i++) {
			for (int j = 0; j < Data.GEN_SIZE; j++) {
				if (individual[i][j] != 0) {
					geneCheck[i][j] = true;
				}
			}
		}
		return geneCheck;
	}

	public boolean[][] getGeneCheck() {
		return geneCheck;
	}

	public void setGeneCheck(boolean[][] geneCheck) {
		this.geneCheck = geneCheck;
	}
}
