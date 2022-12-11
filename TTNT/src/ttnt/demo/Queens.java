package ttnt.demo;

import java.util.Arrays;

public class Queens {

	final int SIZE = 4;
	int[] queens = new int[SIZE];

	public void outPut() {
//		System.out.println(Arrays.toString(queens));
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (queens[i] == j) {
					System.out.print(j + "   ");
				}
				System.out.print("|  ");
			}
			System.out.println();
		}
	}

	public boolean canPut(int i, int j) {
		for (int k = 0; k < i; k++) {
			if (queens[k] == j || (Math.abs(i - k) == Math.abs(queens[k] - j))) {
				return false;
			}
		}
		return true;
	}

	public void putQueens(int i) {
		for (int j = 0; j < SIZE; j++) {
			System.out.println(i);
			if (canPut(i, j)) {
				queens[i] = j;

				if (i == SIZE - 1) {
					outPut();
				}
				putQueens(i + 1);
				queens[i] = 0;
			}

		}
//		System.out.println(Arrays.toString(queens));
	}

	public static void main(String[] args) {
		Queens q = new Queens();
//		q.putQueens(0);
		
		
	}

}
