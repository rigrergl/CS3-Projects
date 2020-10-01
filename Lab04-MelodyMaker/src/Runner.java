import java.util.LinkedList;
import java.util.Queue;

public class Runner {

	public static void main(String[] args) {
		QueueProbs qp = new QueueProbs();
		
//		System.out.println(qp.evenFirst(asQueue(new int[] {3, 5, 4, 17, 6, 83, 1, 84, 16, 37})));
//		System.out.println(qp.allPrimeNumsUpTo(120));
	}
	
	
	public static Queue<Integer> asQueue(int[] nums){
		Queue<Integer> queue = new LinkedList<Integer>();
		for(int a: nums) {
			queue.offer(a);
		}
		
		return queue;
	}
}
