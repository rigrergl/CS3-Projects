import java.util.LinkedList;
import java.util.Queue;

public class GemList 
{
	
	private Node head;
	private Node tail;
	private int size;
	
	public GemList() {
	}
	
	public int size() {
		return this.size;
	}
	
	public void draw(double y) {
		//define a gem width variable
		//gem width = (1 - .1) / max gems
		//.05 empty screen on both sides
		
		
		double marginSpace = .05;
		
		double gemWidth = (1 - 2*marginSpace) / GemGame.MAX_GEMS;
				
		double x = marginSpace;
		Node current = this.head;
		while(current != null) {
			current.gem.draw(x, y);
			current = current.next;
			x += gemWidth;
		}
	}
	
	@Override
	public String toString() {
		if(this.size == 0) {
			return "[]";
		}
		
		String toString = "[";
		Node current = this.head;
		toString += current.gem.toString();
		current = current.next;
		while(current != null) {
			toString += ", " + current.gem.toString();
			current = current.next;
		}
		
		toString += "]";
		return toString;
	}
	
	public void insertBefore(Gem gem, int index) {
		if(index < 0)
			throw new IndexOutOfBoundsException();
		
		if(this.size == 0) {
			this.head = new Node(gem);
			this.tail = this.head;
			size++;
			return;
		}
		
		else if(index >= this.size) {
			this.tail.next = new Node (gem);
			this.tail = this.tail.next;
			size++;
			return;
		}
		
		int i = 0;
		Node current = this.head;
		while(i != index-1) {
			current = current.next;
			i++;
		}
		
		Node temp = current.next;
		current.next = new Node(gem);
		current.next.next = temp;
		
		size++;
		//TODO TEST THIS METHOD
	}
	
	public int score() {
		/*
		 * cycle through the linked list
		 * keep a type variable
		 * keep adding any new gems to colorGroups queue
		 * if type of current gem != type previousType: empty out the queue, make its type the new previous type
		 */
		
		if(this.size == 0) {
			return 0;
		}
		
		
		Node current = this.head;
		Queue<Gem> colorGroup = new LinkedList<>();
		GemType previousType = current.gem.getType();
		
		int totalPoints = 0;
		
		while(current != null) {
			if(current.gem.getType() == previousType) {
				colorGroup.offer(current.gem);
				
				current = current.next;
				continue;
			}
			
			else {
				//empty out queue
				int pointsToAdd = 0;
				
				int streak = 0;
				while(!colorGroup.isEmpty()) {
					streak++;
					pointsToAdd += colorGroup.poll().getPoints();
				}
				
				totalPoints += streak * pointsToAdd;
				
				//making a new color group
				previousType = current.gem.getType();
			}
		}
		
		int streak = 0;
		int pointsToAdd = 0;
		while(!colorGroup.isEmpty()) {
			streak ++;
			pointsToAdd += colorGroup.poll().getPoints();
		}
		totalPoints += streak * pointsToAdd;
		
		return totalPoints;
	}
	
	
	
	/////////// Inner Class //////////////////////
	private class Node{
		private Gem gem;
		private Node next;
		
		public Node(Gem gem) {
			this.gem = gem;
			this.next = null;
		}
	}
	/////////////////////////////////////////////
	
	
	
	public static void main(String [] args)
	{
		GemList list = new GemList();
		System.out.println(list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.9);		
		
		list.insertBefore(new Gem(GemType.BLUE, 10), 0);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.8);
		
		list.insertBefore(new Gem(GemType.BLUE, 20), 99);  //not a mistake, should still work
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.7);
		
		list.insertBefore(new Gem(GemType.ORANGE, 30), 1);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.6);
		
		list.insertBefore(new Gem(GemType.ORANGE, 10), 2);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.5);
		
		list.insertBefore(new Gem(GemType.ORANGE, 50), 3);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.4);
		
		list.insertBefore(new Gem(GemType.GREEN, 50), 2);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.3);		
	}	
}
