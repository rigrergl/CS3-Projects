import java.util.HashMap;
import java.util.Map;

public class WelcomeBack {

	public WelcomeBack() {}
	
	public String getMiddle(String str) {
		return (str.length()%2==0)? str.substring(str.length()/2 - 1, str.length()/2 +1 ) : str.substring(str.length()/2 , str.length()/2 +1) ;
	}
	
	public int[] sumNumbers(int n) {
		int[] sums = new int[n+1];
		int sum = 0;
		
		for(int i=0; i<=n; i++) {
			sum += i;
			sums[i] = sum;
		}
		
		return sums;
	}
	
	public int sumDigits(int num) { //12. already used recursion on first implementation
		if(num /10 == 0) return num;
		
		else return (num %10) + sumDigits(num/10);
	}
	
	public int keepSummingDigits(int num) {
		if(num /10 ==0) return num;
		else return keepSummingDigits(sumDigits(num));
	}
	
	public String getIntersection(int[]a, int [] b) {
		String intersection = "";
		
		for(int i = 0; i < a.length; i++) {
			if(intersection.contains(String.valueOf(a[i]))) {continue;}
			for(int ii = 0; ii < b.length; ii++) {
				if(intersection.contains(String.valueOf(a[i]))) {continue;}
				if(b[ii] == a[i]) intersection += a[i]+"";
				
			}
		}
		
		return intersection;
	}
	
	public Map<Integer, Integer> mapLengths(String[] words){
		Map<Integer, Integer> mapLengths = new HashMap();
		
		for(int i=0; i<words.length; i++) {
			int wordLength = words[i].length();
			if(mapLengths.containsKey(wordLength)) mapLengths.put(wordLength, mapLengths.get(wordLength) + 1);
			else mapLengths.put(wordLength, 1);
			
		}
		
		return mapLengths;
	}
	
	public int sumWithoutCarry(int a, int b) {
		int result = 0;
		int place = 1; //keeps track of where to place new digits
		
		while(a > 0) {
			System.out.println(a + " " + b);
			
			int newDigit = a%10 + b%10;
			if(newDigit >= 10) newDigit -= 10;
			result = (newDigit*place) + result;
			
			place *= 10;
			a/= 10;
			b/= 10;
		}
		
		return result;
	}
	
	public int buySell(int[] stocks) {
		int maxProfit = 0;
//		for(int boughtIndex = 0; boughtIndex < stocks.length -1; boughtIndex++) {}
		int boughtIndex = 0; //change
		//let's say you bought at 0, let's find the maximum profit
		for(int i=boughtIndex+1; i<stocks.length; i++) {
			if(stocks[i] - stocks[boughtIndex] > maxProfit) maxProfit = stocks[i] - stocks[boughtIndex];
		}
	
		return maxProfit;
	}
	
	
}
