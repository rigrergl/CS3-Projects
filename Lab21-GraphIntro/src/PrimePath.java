import java.io.File;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class PrimePath {
	public static void main(String[] args) {
		PrimeGraph graph = new PrimeGraph();
		System.out.println("Input: 1033 8179\nOuput: " + graph.shortestPath("1033", "8179"));
		System.out.println();
		System.out.println("Input: 1373 8017\nOuput: " + graph.shortestPath("1373", "8017"));
		System.out.println();
		System.out.println("Input: 1033 1033\nOuput: " + graph.shortestPath("1033", "1033"));
	}
	
	private static class PrimeGraph{
		private HashSet <String> primes = new HashSet<>();
		
		public PrimeGraph() {
			File primesFile = new File("4 Digit Primes");
			Scanner sc = null;
			try {sc = new Scanner(primesFile);} catch(Exception e) {e.printStackTrace(); return;}
			
			while(sc.hasNext()) {
				primes.add(sc.next());
			}
		}
		
		public  String shortestPath(String source, String target) {
			Queue<Vertex> toVisit = new LinkedList<>();
			toVisit.add(new Vertex(source, 0, source));
			while(toVisit.peek() != null) {
				Vertex v = toVisit.poll();
				if(v.prime.equals(target)) {
					return v.stepsFromStart + " steps, " + v.pathFromStart;
				}
				
				//TODO if the adjList actually has values for this prime
				for(int place = 0; place <= 3; place++) {
					for(char digit = 48; digit <= 57; digit++) {
						StringBuilder adjNum = new StringBuilder(v.prime);
						adjNum.setCharAt(place, digit);
						String neighbor = adjNum.toString();
						if(this.primes.contains(neighbor) && !v.pathFromStart.contains(neighbor)) {
							Vertex adjV = new Vertex(neighbor, v.stepsFromStart + 1, v.pathFromStart + " " + neighbor);
							toVisit.offer(adjV);
						}
					}
				}
			}
			
			return "No Path Found";
		}
		
		private class Vertex{
			String prime;
			int stepsFromStart;
			String pathFromStart;
			
			public Vertex(String prime, int stepsFromStart, String pathFromStart) {
				this.prime = prime;
				this.stepsFromStart = stepsFromStart;
				this.pathFromStart = pathFromStart;
			}
		}
	}
}
