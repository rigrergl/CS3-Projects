import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class LazyCoder {
	public static void main(String[] args) {
		File file = new File("lazy.txt");
		Scanner sc = new Scanner(System.in);
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		int testCases = sc.nextInt();
		for (int i = 0; i < testCases; i++) {
			////

			int numContracts = sc.nextInt();
			PriorityQueue<Contract> contractQueue = new PriorityQueue<>(); // earliest deadlines first
			for (int contract = 0; contract < numContracts; contract++) {
				Contract newContract = new Contract(sc.nextInt(), sc.nextInt(), sc.nextInt());
				contractQueue.offer(newContract);
			}

			int currentDay = 0;
			double price = 0;
			while (contractQueue.peek() != null) {
				Contract next = contractQueue.poll();
				if (currentDay + next.time <= next.deadline) {
					currentDay += next.time;
					continue;
				} else { //we don't have enough time until the deadline

					/*
					 * determine the amount you need to pay in order to get the contract done by the
					 * deadline add that to price add the time it took to finish this contract to
					 * day
					 */
					int daysAvailable = next.deadline - currentDay;
					double priceForInstantCompletion = (double) next.time / next.costMultiplier;
					double priceForCompletionByDeadline = (double) -1 * (daysAvailable - next.time) / next.costMultiplier;
					double currentCost = 0;
					
					if(priceForInstantCompletion == 0 || priceForCompletionByDeadline == 0)
						System.out.println("one of them is 0");
					
					if(priceForCompletionByDeadline < priceForInstantCompletion) {
						currentDay += daysAvailable;
						price += priceForCompletionByDeadline;
					}
					else {
						price += priceForInstantCompletion;
					}
					
				}
			}

			System.out.println(price);
			////
		}
	}

	/*
	 * determining the amount you need to pay extra in order to get a contract done
	 * by a deadline:
	 * 
	 * time substraction = (time - costMultipler * dollars) instant completion cost
	 * = time/costMultiplier
	 * 
	 * number of days available = deadline - currentDay
	 */

//	public static double extraPayment(int costMultiplier, int timeAvailable, double priceForInstant) {
//		return -1;
//	}

	private static class Contract implements Comparable<Contract> {
		private Integer deadline;
		private int time;
		private int costMultiplier;

		public Contract(int costMultiplier, int time, int deadline) {
			this.costMultiplier = costMultiplier;
			this.time = time;
			this.deadline = deadline;
		}

		@Override
		public int compareTo(Contract other) {
			return this.deadline.compareTo(other.deadline);
		}

		@Override
		public String toString() {
			return this.costMultiplier + " " + this.time + " " + this.deadline;
		}

	}
}
