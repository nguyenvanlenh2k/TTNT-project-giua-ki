package ttnt.tuan3;

import java.util.*;

public class Node {
	int n;
	List<Integer> state;

	public Node(int n) {
		this.n = n;
		this.state = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			Random r = new Random();
			this.state.add(r.nextInt(n));
		}
	}

	public Node(int n, List<Integer> state) {
		this.n = n;
		this.state = state;
	}
}
