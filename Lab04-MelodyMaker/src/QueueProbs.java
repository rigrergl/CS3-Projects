import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class QueueProbs {

	
	public Queue<Integer> evenFirst(Queue<Integer> nums){
		Queue<Integer> evens = new LinkedList();
		Queue<Integer> odds = new LinkedList();
		
		while(nums.peek() != null) {
			if(nums.peek() % 2 == 0) //even 
				evens.offer(nums.poll());
			
			else odds.offer(nums.poll());
		}
		
		while(odds.peek() != null) {
			evens.offer(odds.poll());
		}
		
		return evens;
	}
	
	public Stack<Integer
	> allPrimeNumsUpTo(int n){
		Queue<Integer> allNums = new LinkedList<>();
		for(int i = 2; i <= n; i++) {
			allNums.offer(i);
		}
		
		Stack<Integer> primes = new Stack<>();
		
		
		while(allNums.peek() != null) {
			primes.push(allNums.poll());
			
			int multipleOfPrime = primes.peek() * 2;
			while(multipleOfPrime <= n) {
				allNums.remove(multipleOfPrime);
				multipleOfPrime += primes.peek();
			}
		}
		
		return primes;
		
	}
}
