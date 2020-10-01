import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Vertex implements Comparable<Vertex>{
	int ID, x, y;
	int prevID = -1;
	boolean visited;
	private Set<Integer> edges;
	double distance = Double.POSITIVE_INFINITY;
	
	public Vertex(int ID, int x, int y) {
		this.ID = ID;
		this.x = x;
		this.y = y;
		this.visited = false;
		edges = new HashSet<>();
	}
	
	public void addEdge(int edgeID) {
		this.edges.add(edgeID);
	}
	
	public void printEdges() {
		for(int e: edges) {
			System.out.println(ID + " " + e);
		}
	}
	
	public Iterator<Integer> getEdgeIterator() {
		return edges.iterator();
	}
	
	public double distanceTo(Vertex other) {
		return Math.sqrt(Math.pow(other.y - this.y, 2) + Math.pow(other.x - this.x, 2));
	}
	
	@Override
	public int compareTo(Vertex other) {
		return (int)(this.distance - other.distance);
	}
	
	@Override
	public boolean equals(Object obj) {
		Vertex other;
		if(obj instanceof Vertex) {
			other = (Vertex) obj;
		}
		else 
			return false;
		
		return other.ID == this.ID;
	}
	
	@Override
	public int hashCode() {
		System.out.println(this.ID);
		return this.ID;
	}
	
}
