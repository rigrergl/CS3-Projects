import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BinaryMaze {
	private int[][] grid;
	Location startLocation;
	Location targetLocation;
	
	public BinaryMaze(File mazeFile) throws FileNotFoundException {
		Scanner sc = new Scanner(mazeFile);
		int rows = sc.nextInt();
		int cols = sc.nextInt();
		
		grid = new int[rows][cols];
		initializeMatrix(sc);
		
		startLocation = new Location(sc.nextInt(), sc.nextInt(), 0);
		targetLocation = new Location(sc.nextInt(), sc.nextInt());
	}
	
	private void initializeMatrix(Scanner sc) {
		for(int r = 0; r < grid.length; r++) {
			for(int c = 0; c < grid[0].length; c++) {
				grid[r][c] = sc.nextInt();
			}
		}
	}
	
	@Override
	public String toString() {
		String s = "";
		for(int r = 0; r < grid.length; r++) {
			s += Arrays.toString(grid[r]) + "\n";
		}
		s += "Start Location:" + startLocation.toString() + "\n";
		s+= "Target Location: " + targetLocation.toString() + "\n";
		return s;
	}

	public int shortestPathLength() {
		//declare vector directions
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, 1, -1};
		
		Queue<Location> toVisit = new LinkedList<>();
		toVisit.offer(this.startLocation);
		
		while(toVisit.peek() != null) {
			Location currentLocation = toVisit.poll();
			if(currentLocation.equals(targetLocation))
				return currentLocation.distanceFromStart;
			
//			currentLocation.visited = true;
			
			//add all unvisited neighbors to the Queue
			for(int i = 0; i < dr.length; i++) {
				int r = currentLocation.getR() + dr[i];
				int c = currentLocation.getC() + dc[i];
				if(r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == 0)
					continue;
				
				grid[r][c] = 0;
				Location neighbor = new Location(r, c, currentLocation.distanceFromStart + 1);
				toVisit.offer(neighbor);
			}
			
		}
		
		return -1; //TODO
	}
	
	private class Location {
		private int r;
		private int c;
		public int distanceFromStart;
		public boolean visited = false;
		
		public Location(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		public Location(int r, int c, int distanceFromStart) {
			this(r,c);
			this.distanceFromStart = distanceFromStart;
		}
		
		public int getR() {return r;}
		public int getC() {return c;}
		
		@Override
		public String toString() {
			return "(" + r + ", " + c + ")";
		}
		
		@Override
		public boolean equals(Object obj) {
			if(!(obj instanceof Location))
				throw new IllegalArgumentException("Tried to compare a Location with some other type of object");
			
			Location other = (Location) obj;
			return other.r == r && other.c == c;
		}
	}
	
	public static void main(String[] args) {
		try {
			long startTime = System.currentTimeMillis();
			BinaryMaze maze = new BinaryMaze(new File("maze.txt"));
			long endTime = System.currentTimeMillis();
			
			System.out.println(maze.shortestPathLength());
			System.out.println("Time: " + ((double)(endTime - startTime) / 1000) + "s");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
