package ttnt.tuan3;

import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;

public class LocalSearch {

	public int checkHorizontal(Node node) {
		int result = 0;
		for (int i = 0; i < node.n - 1; i++) {
			for (int j = i + 1; j < node.n; j++) {
				if (node.state.get(i) == node.state.get(j))
					result++;
			}
		}
		return result;
	}

	public int checkDiagonal(Node node) {
		int result = 0;
		for (int i = 0; i < node.n - 1; i++) {
			for (int j = i + 1; j < node.n; j++) {
				if (Math.abs(i - j) == Math.abs(node.state.get(i) - node.state.get(j)))
					result++;
			}
		}
		return result;
	}
	public int heuristic(Node node) {
		return checkDiagonal(node) + checkHorizontal(node);
	}

	public int tryMovingOneQueen(Node node, int x, int y) {
		node.state.set(y, x);
		return heuristic(node);
	}

	public SortedMap<Integer, Node> generateNeighbours(Node node) {
		SortedMap<Integer, Node> result = new TreeMap<>();
		Random ran = new Random();
		for (int i = 0; i < node.n; i++) {
			int x = ran.nextInt(node.n);
			int y = ran.nextInt(node.n);
			if (result.isEmpty()) {
				result.put(tryMovingOneQueen(node, x, y), node);
			}
			if (result != null) {
				result.put(tryMovingOneQueen(node, x, y), node);

			}
		}
		return result;
	}

	public void run() {
		Node initial = new Node(4); // hoặc 4,5,6,7
		if (heuristic(initial) == 0) // goal
		{
			System.out.println(initial.state);
			return;
		}
		System.out.println("Initial state is: " + initial.state);
		Node node = initial;
		SortedMap<Integer, Node> neighbours = generateNeighbours(node);
		Integer bestHeuristic = neighbours.firstKey();
		while (bestHeuristic < heuristic(node)) {
			node = neighbours.get(bestHeuristic);
			neighbours = generateNeighbours(node);
			bestHeuristic = neighbours.firstKey();
		}
		if (heuristic(node) == 0) {
			System.out.println("Goal is: " + node.state);
		} else
			System.out.println("Cannot find goal state! Best state is: " + node.state);
			System.out.println("So heuristic: "+ heuristic(node));
	}

	public static void main(String[] args) {
		(new LocalSearch()).run();
	}

}
