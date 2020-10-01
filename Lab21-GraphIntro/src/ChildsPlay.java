import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ChildsPlay {
	/*
	 * Plan:
	 * 
	 * 1. Initialize Graph 2.
	 */

	public static void main(String[] args) {
		processFile(new File("ChildsPlay/play.dat"));
		processFile(new File("ChildsPlay/play2.dat"));
	}

	public static void processFile(File file) {
		Scanner sc = null;
		try {
			sc = new Scanner(file);
		} catch (Exception e) {
			System.out.println("Error: File could not be Opened");
			e.printStackTrace();
			return;
		}

		int testCases = sc.nextInt();
//		sc.nextLine();
		for (int i = 0; i < testCases; i++) {
//			int numDominoes = sc.nextInt();
			int edges = sc.nextInt();
			int knockedByHand = sc.nextInt();

			// Initialize the Graph
			DominoGraph graph = new DominoGraph();
			for (int e = 0; e < edges; e++) {
				graph.addConnection(sc.nextInt(), sc.nextInt());
			}

			// Tip over dominoes
			for (int d = 0; d < knockedByHand; d++) {
				graph.tip(sc.nextInt());
			}

			System.out.println(graph.getFallenNum());
		}

		sc.close();
	}

	private static class DominoGraph {
		/**
		 * Directed Graph
		 */
		private HashMap<Integer, Set<Integer>> adjList = new HashMap<>(); // (n, set of all dominoes n will tip)
		private Set<Integer> dominoesTipped = new HashSet<>();

//		public DominoGraph() {}

		public void addConnection(int n1, int n2) {
			if (adjList.containsKey(n1)) {
				adjList.get(n1).add(n2);
			} else {
				Set<Integer> set = new HashSet<>();
				set.add(n2);
				adjList.put(n1, set);
			}
		}

		public void tip(int n) {
			if (dominoesTipped.contains(n))
				return;

			dominoesTipped.add(n);
			Set<Integer> neighbors = adjList.get(n);
			if (neighbors != null)
				for (int n2 : neighbors) {
					tip(n2);
				}
		}

		public int getFallenNum() {
			return dominoesTipped.size();
		}

		@Override
		public String toString() {
			return adjList.toString();
		}
	}
}
