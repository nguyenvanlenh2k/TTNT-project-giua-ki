package ttnt.tuan2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DFS {

	public Node dfsUsingStack(Node initial, int goal) {

		Node node = initial;
		if (node.state.size() == goal) {
			System.out.println(initial.state.toString());
		} else {

			Stack<Node> stack = new Stack<Node>();

			stack.push(initial);
			initial.visited = true;
			while (!stack.isEmpty()) {
				Node p = stack.pop();
				if (p.state.size() == goal) {
					String s = "";
					while (p != initial) {
						s = p.state.toString() + " " + s;
					}
					System.out.println(s);
				}

				// duyet Node ke p
				for (Node v : p.getNeighbours()) {
					if (!v.visited) {
						stack.push(v);
						v.visited = true;

					}

				}
			}
		}
		return null;
	}

}
