import java.util.Stack;

public class SnowflakeProbs {

	public static void main(String[] args) {
//		System.out.println(sumReciprocals(10));
//		System.out.println(productOfEvens(4));
//		
//		Stack<Integer> doubleUp = new Stack<>();
//		doubleUp.push(3); doubleUp.push(7); doubleUp.push(12); doubleUp.push(9); 
//		System.out.println(doubleUp);
//		doubleUp(doubleUp);
//		System.out.println(doubleUp);

//		countToBy(25,4);

//		System.out.println(matchingDigits(23123, 123));

//		printThis(8);
		
//		printNums2(10);

	}

	//////////////////////////////////////////////////////
	public static double sumReciprocals(int n) {
		return sumReciprocals(1, n);
	}

	private static double sumReciprocals(double current, double n) {
		if (current == n)
			return (1 / n);

		else
			return (1 / current) + sumReciprocals(current + 1, n);
	}

	//////////////////////////////////////////////////////
	public static int productOfEvens(int n) {
		return productOfEvens(0, 2, n);
	}

	private static int productOfEvens(int evenIndex, int evenNumber, int n) {
		if (evenIndex == n - 1)
			return evenNumber;

		return evenNumber * productOfEvens(evenIndex + 1, evenNumber + 2, n);
	}

	//////////////////////////////////////////////////////
	public static void doubleUp(Stack<Integer> nums) {
		Stack<Integer> copy = new Stack<>();
		copy(nums, copy);

//		System.out.println("copy: " + copy);
//		System.out.println("nums: " + nums);
		copyDouble(copy, nums);
	}

	private static void copy(Stack<Integer> original, Stack<Integer> copy) { // TODO TEST THIS METHOD
		if (original.isEmpty())
			return;

		else {
			copy.push(original.pop());
			copy(original, copy);
		}
	}

	private static void copyDouble(Stack<Integer> original, Stack<Integer> copy) {
		if (original.isEmpty()) {
			return;
		}

		else {
			copy.push(original.pop());
			copy.push(copy.peek());
			copyDouble(original, copy);
		}
	}

	//////////////////////////////////////////////////////
	public static void countToBy(int n, int m) {
		countToBy(n, m, n);
	}

	private static void countToBy(int n, int m, int current) {
		if (current < 0) {
			return;
		} else {
			countToBy(n, m, current - m);
			System.out.print(current + ", ");
		}

	}
	////////////////////////////////////////////////////

	public static int matchingDigits(int a, int b) {
		return matchingDigits(a, b, false);
	}

	public static int matchingDigits(int a, int b, boolean checkedFirst) {
		if ((a == 0 || b == 0) && checkedFirst)
			return 0;

		if (a % 10 == b % 10)
			return 1 + matchingDigits(a / 10, b / 10, true);

		else
			return matchingDigits(a / 10, b / 10, true);
	}

	////////////////////////////////////////////////////
	public static void printThis(int n) {
		boolean originalEven = (n % 2 == 0);
		printThis(n, originalEven);
	}

	public static void printThis(int n, boolean originalEven) {
		if (n <= 1) {
			if (originalEven)
				System.out.print("**");
			else
				System.out.print("*");

			return;
		}

		if (n % 2 != 0) {
			System.out.print("<");
			printThis(n - 1, originalEven);
			System.out.print(">");
		} else
			printThis(n - 1, originalEven);

	}
	
	/////////////////////////////////////////////////////
	public static void printNums2(int n) {
		boolean originalEven = (n % 2 == 0);
		
		int biggestOuter = n;
		if(n % 2 != 0)
			biggestOuter++;
		
		biggestOuter /= 2;
		
		printNums2(n, originalEven, biggestOuter);
	}

	public static void printNums2(int n, boolean originalEven, int outerNum) {
		if (n <= 1) {
			if (originalEven)
				System.out.print("11");
			else
				System.out.print("1");

			return;
		}

		
		if (n % 2 != 0) {
			System.out.print(outerNum);
			printNums2(n - 1, originalEven, outerNum - 1);
			System.out.print(outerNum);
		} else
			printNums2(n - 1, originalEven, outerNum);

	}
	

}
