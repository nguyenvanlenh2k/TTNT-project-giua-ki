package ttnt.tuan1;

import java.util.ArrayList;
import java.util.List;

public class Node {

	private int state;
	private boolean visited;
	private List<Node> neighbours;
	private Node parent;

	public int getState() {
		return state;
	}
	
	public Node() {
		// TODO Auto-generated constructor stub
	}

	public boolean isVisited() {
		return visited;
	}
	

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}

	public Node(int state) {
		this.state = state;
		this.neighbours = new ArrayList<Node>();
		this.parent = null;
	}

	public void addNeighbours(Node neighbourNode) {
		this.neighbours.add(neighbourNode);
	}

	public List<Node> getNeighbours() {
		return neighbours;
	}
	
	public static void main(String[] args) {
		Node n = new Node();
		System.out.println(n.isVisited());
	}

}
