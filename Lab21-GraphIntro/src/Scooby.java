

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Scooby {
	
	public static void main(String[] args) {
		File file = new File("scooby.dat");
		Scanner sc;
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		int n = sc.nextInt();
		sc.nextLine();
		for(int i = 0; i < n; i ++) {
			String edgeList = sc.nextLine();
			Graph g = new Graph(edgeList);
			String xy = sc.nextLine();
			
			boolean isPathPossible = g.isPathPossible(xy.substring(0,1), xy.substring(1));
			if(isPathPossible)
				System.out.println("yes");
			else
				System.out.println("no");
		}
		sc.close();
	}
	
	private static class Graph{
		private HashMap<String, String> adjList; // (room, possible destinations) 
		
		public Graph(String seed) {
//			System.out.println("Seed: " + seed);
			this.adjList = new HashMap<>();
			
			Scanner sc = new Scanner(seed);
			while(sc.hasNext()) {
				updateGraph(sc.next());
			}
			sc.close();
			
//			System.out.println(adjList.toString());
		}

		private void updateGraph(String edge) {
			String a = edge.substring(0, 1); 
			String b = edge.substring(1);
			
			if(!adjList.containsKey(a)) {
				adjList.put(a, b);
			}
			else {
				String prev = adjList.remove(a);
				prev += b;
				adjList.put(a, prev);
			}
			
			if(!adjList.containsKey(b)) {
				adjList.put(b, a);
			}
			else {
				String prev = adjList.remove(b);
				prev += a;
				adjList.put(b, prev);
			}
		}
		
		public boolean isPathPossible(String startRoom, String targetRoom) {
			Queue<String> roomsToVisit = new LinkedList<>();
			roomsToVisit.offer(startRoom);
			String visitedRooms = ""; //rooms enqueued
			
			while(roomsToVisit.peek() != null) {
				String currentRoom = roomsToVisit.poll();
				if(currentRoom.equals(targetRoom))
					return true;
				
				if(!adjList.containsKey(currentRoom)) {
					continue;
				}
				
				char[] neighborRooms = adjList.get(currentRoom).toCharArray();
				for(char c: neighborRooms) {
					if(visitedRooms.contains(c + ""))
						continue;
					
					roomsToVisit.offer(c + "");
					visitedRooms += c;
				}
			}
			
			return false;
		}
	}
}
