package ttnt.tuan1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DFS {

	public void dfsUsingStack(Node initial, Node goal) {
		ArrayList<Node> listVisited = new ArrayList<>();

		Node node = initial;
		if (node.getState() == goal.getState()) {
			System.out.println(initial.getState());
		}

		Stack<Node> stack = new Stack<Node>();

		stack.push(initial);

		while (!stack.isEmpty()) {

			Node p = stack.pop();
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
					stack.push(v);
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
//		System.out.println(listVisited.size());

	}

}
