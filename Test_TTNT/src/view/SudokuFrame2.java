package view;

import java.awt.*;
//import Sudoku.HighScore;
import java.awt.event.*;
import java.util.Arrays;
import java.util.List;

import javax.swing.*;
import javax.swing.border.MatteBorder;

import figures.Data;



public class SudokuFrame2 extends JFrame implements ActionListener {
	String str;
	int I, J;
	boolean endDq = false;
	JPanel pn, pn2;
	JButton newGame_bt, start_bt;
	public static JButton bt[][] = new JButton[9][9];
	//ket qua
	int[][] resultArr = new int[9][9];
	//mang check
	public SudokuFrame2() {
		init();
	}

	public void init() {
		Container cn = this.getContentPane();
		pn2 = new JPanel();
		pn2.setLayout(new FlowLayout());

		newGame_bt = new JButton("New Game");
		newGame_bt.addActionListener(this);
		start_bt = new JButton("Start Game");
		start_bt.addActionListener(this);

		pn2.add(newGame_bt);
		pn2.add(start_bt);
		cn.add(pn2, "North");

		pn = new JPanel();
		pn.setLayout(new GridLayout(9, 9));
		// tao 81 button
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++) {
				bt[i][j] = new JButton();
				bt[i][j].addActionListener(this);
//				bt[i][j].addKeyListener(this);
				bt[i][j].setActionCommand(i + " " + j);
				bt[i][j].setBackground(Color.white);
				bt[i][j].setFont(new Font("UTM Micra", 1, 30));
				bt[i][j].setForeground(Color.black);
				pn.add(bt[i][j]);
			}
		// thêm khung màu đen
		for (int i = 0; i < 9; i += 3)
			for (int j = 0; j < 9; j += 3) {
				bt[i][j].setBorder(BorderFactory.createMatteBorder(3, 3, 1, 1, Color.black));
				bt[i][j + 2].setBorder(BorderFactory.createMatteBorder(3, 1, 1, 3, Color.black));
				bt[i + 2][j + 2].setBorder(BorderFactory.createMatteBorder(1, 1, 3, 3, Color.black));
				bt[i + 2][j].setBorder(BorderFactory.createMatteBorder(1, 3, 3, 1, Color.black));
				bt[i][j + 1].setBorder(BorderFactory.createMatteBorder(3, 1, 1, 1, Color.black));
				bt[i + 1][j + 2].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 3, Color.black));
				bt[i + 2][j + 1].setBorder(BorderFactory.createMatteBorder(1, 1, 3, 1, Color.black));
				bt[i + 1][j].setBorder(BorderFactory.createMatteBorder(1, 3, 1, 1, Color.black));
				bt[i + 1][j + 1].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
			}

		cn.add(pn);
		this.setVisible(true);
		this.setSize(700, 700);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
			// nhấn nút start
			String s = e.getActionCommand();
			if(s.equals("Start Game")) printArr();
			// nhấn nút new game
			if (s.equals("New Game")) {
				this.newGame();
			}
			// nhập các nút
			if(!s.equals("Start Game") && !s.equals("New Game")) {
				// tách chuỗi từ bt[][]
				int k = s.indexOf(32);
				int i = Integer.parseInt(s.substring(0, k));
				int j = Integer.parseInt(s.substring(k + 1, s.length()));
				// gán I J để xử lý nhập vào
				I = i;
				J = j;
			}
	}

	private void printArr() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(resultArr[i][j]+" ");
			}
			System.out.println();
		}
	}

	private void newGame() {
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++) {
				bt[i][j].setText("");
			}
	}

	public static void main(String[] args) {
		SudokuFrame2 sudoku = new SudokuFrame2();
		int[][] individual = { { 0, 0, 4, 0, 5, 0, 0, 6, 0 }, { 0, 6, 0, 1, 0, 0, 8, 0, 9 },
				{ 3, 0, 0, 0, 0, 7, 0, 0, 0 }, { 0, 8, 0, 0, 0, 0, 5, 0, 0 }, { 0, 0, 0, 4, 0, 3, 0, 0, 0 },
				{ 0, 0, 6, 0, 0, 0, 0, 7, 0 }, { 0, 0, 0, 2, 0, 0, 0, 0, 6 }, { 1, 0, 5, 0, 0, 4, 0, 3, 0 },
				{ 0, 2, 0, 0, 7, 0, 1, 0, 0 } };
		for (int row = 0; row < Data.GEN_SIZE; row++) {
			for (int column = 0; column < Data.GEN_SIZE; column++) {
				if(individual[row][column] != 0) {
					bt[row][column].setText(individual[row][column]+"");
				}
			}
		}

	}

//	
	public int heuristic() {

		return cluster() + row() + colum();
	}

//	Tính xung đột theo cụm 3x3
	public int cluster() {
		int result = 0;
		for (int m = 0; m < 9; m += 3) {
			for (int n = 0; n < 9; n += 3) {
				result += clusterSum(m, n);
			}
		}
		return result;
	}

//	Chia mảng thành 9 cụm 
	public int clusterSum(int a, int b) {
		int count = 0;
		for (int i = a; i < a + 3; i++) {
			for (int j = b; j < b + 3; j++) {
//				lấy từng vị trí để so sánh với từng vị trí khác trong mảng
				for (int k = a; k < a + 3; k++) {
					for (int l = b; l < b + 3; l++) {
//						arr[k][l] là vị trí được lấy ra để so sánh với từng arr[i][j], kiểm tra với chính nó
						if(resultArr[k][l] != 0 && resultArr[i][j] != 0 && !(k == i && l == j)) {
//							System.out.println("resultArr[k][l] "+resultArr[k][l]+" ,"+"resultArr[i][j] "+resultArr[i][j]);
							if (resultArr[k][l] == resultArr[i][j]) count++;							
						}
					}
				}
			}
		}
		return count;
	}

	private int colum() {
		int count = 0;
		int size = 9;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				for (int k = 0; k < size; k++) {
					if (resultArr[i][k] != 0 && resultArr[i][j] == resultArr[i][k] && j != k)
						count++;
				}
			}
		}
		return count;
	}

	private int row() {
		int count = 0;
		int size = 9;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				for (int k = 0; k < size; k++) {
					if (resultArr[k][i] != 0 && resultArr[j][i] == resultArr[k][i] && j != k)
						count++;
				}
			}
		}
		return count;
	}


}
