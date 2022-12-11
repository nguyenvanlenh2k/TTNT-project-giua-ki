package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
//import Sudoku.HighScore;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import controller.Sudoku;
import figures.Data;

public class SudokuFrame extends JFrame implements ActionListener, KeyListener {
	String str;
	int I, J;
	boolean endDq = false;
	JPanel pn, pn2;
	JButton newGame_bt, start_bt;
	JButton bt[][] = new JButton[9][9];
	ArrayList<int[][]> arr_source;
	Sudoku sudoku ;
	// ket qua
	int[][] resultArr = new int[9][9];

	// mang check
	public SudokuFrame() {
		init();
		arr_source = new ArrayList<>();

		int[][] kho = { { 0, 0, 4, 0, 0, 0, 0, 6, 0 }, { 0, 0, 0, 1, 0, 0, 8, 0, 9 }, { 3, 0, 0, 0, 0, 7, 0, 0, 0 },
				{ 0, 8, 0, 0, 0, 0, 5, 0, 0 }, { 0, 0, 0, 4, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 7, 0 },
				{ 0, 0, 0, 2, 0, 0, 0, 0, 6 }, { 1, 0, 0, 0, 0, 4, 0, 3, 0 }, { 0, 2, 0, 0, 7, 0, 1, 0, 0 } };
		int[][] trungbinh = { { 5, 3, 0, 0, 7, 0, 0, 0, 0 }, { 6, 0, 0, 1, 9, 5, 0, 0, 0 },
				{ 0, 9, 8, 0, 0, 0, 0, 6, 0 }, { 8, 0, 0, 0, 6, 0, 0, 0, 3 }, { 4, 0, 0, 8, 0, 3, 0, 0, 1 },
				{ 7, 0, 0, 0, 2, 0, 0, 0, 6 }, { 0, 6, 0, 0, 0, 0, 2, 8, 0 }, { 0, 0, 0, 4, 1, 9, 0, 0, 5 },
				{ 0, 0, 0, 0, 8, 0, 0, 7, 9 } };
		int[][] de = { { 0, 0, 7, 8, 0, 0, 9, 0, 0 }, { 0, 0, 0, 5, 0, 0, 0, 3, 1 }, { 9, 0, 0, 0, 0, 1, 0, 4, 0 },
				{ 2, 1, 0, 0, 6, 0, 7, 8, 0 }, { 0, 0, 0, 0, 0, 3, 0, 9, 0 }, { 3, 0, 9, 0, 1, 0, 2, 0, 0 },
				{ 4, 0, 0, 0, 0, 0, 0, 1, 6 }, { 0, 0, 0, 1, 0, 9, 0, 0, 8 }, { 0, 8, 0, 0, 3, 0, 0, 0, 0 } };

		arr_source.add(kho);
		arr_source.add(trungbinh);
		arr_source.add(de);
		
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
				bt[i][j].addKeyListener(this);
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
		if (s.equals("Start Game")) {
			printArr();
			sudoku = new Sudoku();
			sudoku.initPop(resultArr);
			System.out.println(sudoku.genetic());
			

			// in ra kq cuoi cung
			for (int row = 0; row < Data.GEN_SIZE; row++) {
				for (int column = 0; column < Data.GEN_SIZE; column++) {
					if (sudoku.result[row][column] != 0) {
						bt[row][column].setText(sudoku.result[row][column] + "");
					}
				}
			}
		}
		// nhấn nút new game
		if (s.equals("New Game")) {
			this.newGame();
			sudoku = new Sudoku();
			sudoku.initPop(resultArr);
			// ran 1 vi tri trong danh sach mang du lieu
			Random ran = new Random();
			int index_rd = ran.nextInt(arr_source.size());
			int[][] arr_play = arr_source.get(index_rd);
			for (int row = 0; row < Data.GEN_SIZE; row++) {
				for (int column = 0; column < Data.GEN_SIZE; column++) {
					if (arr_play[row][column] != 0) {
						bt[row][column].setText(arr_play[row][column] + "");
						resultArr[row][column] = arr_play[row][column];
					}
				}
			}
		}
		// nhập các nút
		if (!s.equals("Start Game") && !s.equals("New Game")) {
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
				System.out.print(resultArr[i][j] + " ");
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
		SudokuFrame sudoku = new SudokuFrame();

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// chỉ cho phép nhập giá trị số từ 1 đến 9
		int v = e.getKeyCode();
		if ((v >= 49 && v <= 57) || (v >= 97 && v <= 105)) {
			if (v >= 49 && v <= 57)
				v -= 48;
			if (v >= 97 && v <= 105)
				v -= 96;
			// add giá trị nhập vào mảng resultArr[I][J]
			resultArr[I][J] = v;
			bt[I][J].setText(v + "");
			// kiem tra người dùng nhập đúng chưa
			if (heuristic() > 0)
				bt[I][J].setBackground(Color.GRAY);
			else
				bt[I][J].setBackground(Color.white);
		}

	}

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
						if (resultArr[k][l] != 0 && resultArr[i][j] != 0 && !(k == i && l == j)) {
//							System.out.println("resultArr[k][l] "+resultArr[k][l]+" ,"+"resultArr[i][j] "+resultArr[i][j]);
							if (resultArr[k][l] == resultArr[i][j])
								count++;
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

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
