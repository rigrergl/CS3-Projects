import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class PracticeProblems {

	public static void main(String[] args) {
//		printBinary(3);
//		climbStairs(4);
//		campsite(2, 1);
//		System.out.println(getMax(Arrays.asList(7, 30, 8, 22, 6, 1, 14), 19));
//		System.out.println(makeChange(100));
		printChange(1);
	}
	
	//printBinary
	public static void printBinary(int digits) {
		printBinaryHelper(digits, "");
	}
	
	private static void printBinaryHelper(int digits, String result) {
		if(result.length() == digits){
			System.out.print(result + " ");
			return;
		}
		
		printBinaryHelper(digits, result + "0");
		printBinaryHelper(digits, result + "1");
	}

	//climbStairs
	public static void climbStairs(int steps) {
		climbStairs(steps, "");
	}
	
	private static void climbStairs(int steps, String path) {
		if(steps <= 0) {
			System.out.println(path.substring(2));
			return;
		}
		
		climbStairs(steps - 1, path + ", 1");
		
		if(steps - 2 >= 0)
			climbStairs(steps - 2, path + ", 2");
	}
	
	//campsite
	public static void campsite(int x, int y) {
		computePath(x, y, 0, 0, "");
	}
	
	private static void computePath(int campX, int campY, int currentX, int currentY, String path) {
		if(currentX > campX || currentY > campY) {
			return;
		}
		
		if(campX == currentX && campY == currentY) {
			System.out.println(path.substring(2));
			return;
		}
		
		computePath(campX, campY, currentX + 1, currentY, path + ", E");
		computePath(campX, campY, currentX, currentY + 1, path + ", N");
		computePath(campX, campY, currentX + 1, currentY + 1, path + ", NE");
		
	}
	
	//getMax
	public static int getMax(List<Integer> nums, int limit) {
		return getMax(nums, limit, 0, 0);
	}
	
	private static int getMax(List<Integer> nums, int limit, int currentSum, int i) {
		if(currentSum > limit) {
			return Integer.MIN_VALUE;
		}
		
		if(i >= nums.size()) {
			return currentSum;
		}
		
		int sumA = getMax(nums, limit, currentSum + nums.get(i), i+1);
		int sumB = getMax(nums, limit, currentSum, i+1);
		
		
		return Math.max(sumA, sumB);
	}
	
	//make change

	public static int makeChange(int amount) {
		List<Integer> denominations = Arrays.asList(1,5,10,25);
		return recursiveChange(amount, 0, denominations, 0);
	}
	
	private static int recursiveChange(int amount, int current, List<Integer> denominations, int i) {
		if(current > amount || i >= denominations.size())
			return 0;
		if(current == amount)
			return 1;
		
		return recursiveChange(amount, current, denominations, i+1)
				+ recursiveChange(amount, current + denominations.get(i), denominations, i);
		
	}
	
	//make change print
	
	public static void printChange(int amount) {
		List<Integer> denominations = Arrays.asList(1,5,10,25);
		int[] combination = {0,0,0,0};
		
		printChangeHelper(amount, 0, denominations, 0, combination);
	}
	
	private static void printChangeHelper(int amount, int current, List<Integer> denominations, int i, int[] combination) {
		if(current > amount || i >= denominations.size()) {
			return;
		}
		if(current == amount) {
			System.out.println(Arrays.toString(combination));
			return;
		}
		
		int[] newCombo = combination;
		newCombo[i] += 1;
		
		printChangeHelper(amount, current, denominations, i+1, combination);
		printChangeHelper(amount, current + denominations.get(i), denominations, i, newCombo);
		
	}
}
