/*
	Practice assignment 8. 
	Author: Tingbin Huang 
	Class : CS 245 section I
*/

import java.util.Stack;


public class GraphAdjMatrix implements Graph {

	private int size;
	private Edge[] edge;
	private int[] neighborCounter;

	// Constructor
	public GraphAdjMatrix(int size) {
		this.size = size;
		edge = new Edge[size];
		neighborCounter = new int[size];

		// initialize element in 'numberIncident' array.
		for (int num : neighborCounter) {
			neighborCounter[num] = 0;
		}

		for (int i = 0; i < size; i++) {
			edge[i] = null;
		}
	}

	// inner class
	class Edge {
		private int value;
		private Edge next;

		public Edge(int input) {
			this.value = input;
			this.next = null;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int newInput) {
			this.value = newInput;
		}
	}

	@Override
	public void addEdge(int v1, int v2) {
		Edge head = edge[v1];
		neighborCounter[v1]++;

		if (head == null) {
			edge[v1] = new Edge(v2);
		} else {

			while (head.next != null) {
				head = head.next;
			}
			head.next = new Edge(v2);
		}

	}

	@Override
	public void topologicalSort() {

		Stack<Integer> s = new Stack<Integer>();
		int[] number = new int[size];

		// start making change to 'number' array.
		for (int i = 0; i < size; i++) {
			Edge temp = edge[i];

			while (temp != null) {
				number[temp.value]++;
				temp = temp.next;
			}
		}

		// start putting edge to stack.
		for (int i = 0; i < size; i++) {

			if (number[i] == 0) {
				s.push(new Integer(number[i]));
			}
		}

		// Start running stack for popping(where printing happens) and pushing.
		while (!s.isEmpty()) {
			int v = (s.pop());
			System.out.println(v);
			Edge tmp = edge[v];
			while (tmp != null) {
				if (--number[tmp.value] == 0) {
					s.push(new Integer(tmp.value));
				}
				tmp = tmp.next;
			}
		}

	}

	@Override
	public int[] neighbors(int vertex) {
		int[] neighborsArr = new int[neighborCounter[vertex]];
		Edge temp = edge[vertex];
		int i = 0;

		while (temp != null && i < neighborsArr.length) {
			neighborsArr[i] = temp.value;
			temp = temp.next;
			i++;
		}

		return neighborsArr;
	}

	/**
	 * return size of graph.
	 * 
	 * @return int size
	 */
	public int size() {
		return size;
	}

}
