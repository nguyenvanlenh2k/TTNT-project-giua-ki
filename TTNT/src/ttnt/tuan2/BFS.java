package ttnt.tuan2;

import java.util.LinkedList;
import java.util.Queue;

public class BFS {

	public Node bfsUsingQueue(Node initial, int goal) {
		Node result = new Node(goal);

		if (result.state.size() == goal) {
			System.out.println("1X1");
		} else {
			Queue<Node> queue = new LinkedList<Node>();
			queue.offer(initial);
			initial.visited = true;

			while (!queue.isEmpty()) {

				Node p = queue.poll();
				if (p.state.size() == goal) {
					String s = "";
					while (p != initial) {
						result.state.addAll(p.state);
						s = p.state.toString() + "=>" + s;
					}
					System.out.println(s);
				}

				// duyet Node ke p
				for (Node v : p.getNeighbours()) {
					if (!v.visited) {
						queue.offer(v);
						v.visited = true;
					}
				}
			}
		}

		return result;
	}

}
