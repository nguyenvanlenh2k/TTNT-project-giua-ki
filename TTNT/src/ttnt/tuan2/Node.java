package ttnt.tuan2;

import java.util.ArrayList;
import java.util.List;


public class Node {

	int n;
	List<Integer> state;
	List<Node> neighbours;
	boolean visited = false;

	public Node(int n) {
		this.n = n;
		this.state = new ArrayList<>();
		this.neighbours = new ArrayList<>();

	}

	public Node(int n, List<Integer> state) {
		this.n = n;
		this.state = state;
		this.neighbours = new ArrayList<>();

	}

	public void addNeighbours(Node neighbourNode) {
		this.neighbours.add(neighbourNode);
	}

	public boolean isValid(List<Integer> state) {
		if (state.size() == 1)
			return true;
		if (state.size() > 1)
			for (int i = 0; i < state.size() - 1; i++) {
				for (int j = i + 1; j < state.size(); j++) {
					// Kiem tra tren hang ngang
					if (state.get(i) == state.get(j))
						return false;
					// Kiem tra theo duong cheo
					if (Math.abs(i - j) == Math.abs(state.get(i) - state.get(j)))
						return false;
				}
			}
		return true;
	}

	private List<Integer> place(int x) {
		List<Integer> result = new ArrayList<Integer>(this.state);
		result.add(x);
		if (!isValid(result))
			return null;
		return result;
		// sau khi them 1 node
	}

	public List<Node> getNeighbours() {
		if (state.size() == n)
			return null;
		for (int i = 0; i < n; i++) {
			if (place(i) != null)
				addNeighbours(new Node(n, place(i)));

		}
		return neighbours;

	}

}
