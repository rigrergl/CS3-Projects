import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Graph {
	Vertex[] vertices;
	
	public Graph(String filename) {
		File file = new File(filename);
		Scanner sc = null;
		try {sc = new Scanner(file);} catch (FileNotFoundException e) {e.printStackTrace();}
		
		int vertexNum = sc.nextInt();
		int edges = sc.nextInt();
		
		this.vertices = new Vertex[vertexNum];
		
		for(int v = 0; v < vertexNum;  v++) {
			int ID = sc.nextInt();
			int x = sc.nextInt();
			int y = sc.nextInt();
			vertices[ID] = new Vertex(ID, x, y);;
		}
		
		for(int e = 0; e < edges; e++) {
			int v1 = sc.nextInt();
			int v2 = sc.nextInt();
			vertices[v1].addEdge(v2);
			vertices[v2].addEdge(v1);
		}
		
		int source = sc.nextInt();
		int target = sc.nextInt();
		
		System.out.println("Total distance: " +  shortestDistance(source, target));
	}
	
	public double shortestDistance(int source, int target) {
		double distance = -1;
		Queue<Vertex> q = new PriorityQueue<>();
		
		q.add(vertices[source]);
		vertices[source].distance = 0;
		
		while(q.peek() != null) {
			Vertex current = q.poll();
			
			
			current.visited = true;
			Iterator<Integer> edgeIterator = current.getEdgeIterator();
			while(edgeIterator.hasNext()) {
				int neighborID = edgeIterator.next();
				Vertex neighbor = vertices[neighborID];
				double newDistance = current.distance + current.distanceTo(neighbor);
				if(neighbor.distance > newDistance) {
					neighbor.distance = newDistance;
					neighbor.prevID = current.ID;
				}

				if(!neighbor.visited && neighbor.distance < vertices[target].distance) {
					q.offer(neighbor);
				}
			}
		}
		
		if(vertices[target].distance < Double.POSITIVE_INFINITY) {
			distance = vertices[target].distance;
			System.out.print("Path: ");
			printPath(target);
			System.out.println();
		}
		
		return distance;
	}
	
	private void printPath(int vertexID) {
		Vertex v = vertices[vertexID];
		if(v.prevID == -1) {
			System.out.print(v.ID);
			return;
		}
		
		printPath(v.prevID);
		System.out.print(v.ID);
		
	}
	
	public static void main(String[] args) {
		Graph graph = new Graph("input6.txt");
	}
}
