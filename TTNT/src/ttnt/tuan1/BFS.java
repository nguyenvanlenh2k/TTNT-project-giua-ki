package ttnt.tuan1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {

	public void bfsUsingQueue(Node initial, Node goal) {
		ArrayList<Node> listVisited = new ArrayList<>();

		Node node = initial;
		if (node.getState() == goal.getState()) {
			System.out.println(initial.getState());
		}

		Queue<Node> queue = new LinkedList<Node>();

		queue.offer(initial);

		while (!queue.isEmpty()) {

			Node p = queue.poll();
			if (p.getState() == goal.getState()) {
				String s = "";
				while (p != initial) {
					s = p.getState() + " " + s;
					p = p.getParent();
				}

				System.out.println(initial.getState() + " " + s);
				return;
			}

			p.setVisited(true);
			listVisited.add(p);
			// duyet Node ke p
			for (Node v : p.getNeighbours()) {
				if (!v.isVisited()) {
					queue.offer(v);
					v.setVisited(true);
					listVisited.add(v);
					v.setParent(p);

				}
			}
			for (int i = 0; i < listVisited.size(); i++) {
				listVisited.get(i).setVisited(false);
				listVisited.remove(listVisited.get(i));
			}
		}

	}

}
