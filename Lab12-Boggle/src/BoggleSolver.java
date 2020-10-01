import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class BoggleSolver
{
	private HashSet<String> dictionary;
	
	
	// Initializes the data structure using the given array of strings as the dictionary.
	// (You can assume each word in the dictionary contains only the uppercase letters A - Z.)
	public BoggleSolver(String dictionaryName)
	{
		this.dictionary = new HashSet<>();
		
		File dictionary = new File(dictionaryName);
		Scanner sc = new Scanner("temp");
		try {
			sc = new Scanner(dictionary);
		} catch (FileNotFoundException e) {
			System.out.println("Dictionary File Not Found");
			e.printStackTrace();
		}
		
		while(sc.hasNext()) {
			this.dictionary.add(sc.next());
		}
	}

	// Returns the set of all valid words in the given Boggle board, as an Iterable object
	public Iterable<String> getAllValidWords(BoggleBoard board)
	{
		HashSet<String> allWords = new HashSet<>();
		
		
		for(int r = 0; r < board.rows(); r++) {
			for(int c = 0; c < board.cols(); c++) {
				recursiveHelper(board, allWords, "", r,c, new boolean[board.rows()][board.cols()]);
			}
		}
		
		System.out.println(allWords);
		return allWords;
	}
	
	private void recursiveHelper(BoggleBoard board, HashSet<String> allWords, String currentWord, int r, int c, boolean[][] locationsVisited) {
		
		if(r < 0 || r >= board.rows() || c < 0 || c >= board.cols()) {
			return;
		}
		
		if(locationsVisited[r][c] == true) {
			return;
		}
		
		String toAppend = board.getLetter(r, c) + "";
		if(toAppend.equals("Q")) {
			toAppend += "U";
		}
		
		currentWord += toAppend;
		if(dictionary.contains(currentWord) && currentWord.length() > 2) {
			allWords.add(currentWord);
		}
		
		locationsVisited[r][c] = true;
		
		
		recursiveHelper(board, allWords, currentWord, r + 1, c, locationsVisited);
		recursiveHelper(board, allWords, currentWord, r - 1, c, locationsVisited);
		recursiveHelper(board, allWords, currentWord, r, c + 1, locationsVisited);
		recursiveHelper(board, allWords, currentWord, r, c - 1, locationsVisited);
		
		recursiveHelper(board, allWords, currentWord, r - 1, c + 1, locationsVisited);
		recursiveHelper(board, allWords, currentWord, r + 1, c - 1, locationsVisited);
		recursiveHelper(board, allWords, currentWord, r + 1, c + 1, locationsVisited);
		recursiveHelper(board, allWords, currentWord, r - 1, c - 1, locationsVisited);
		
		locationsVisited[r][c] = false;
		
	}
	
	// Returns the score of the given word if it is in the dictionary, zero otherwise.
	// (You can assume the word contains only the uppercase letters A - Z.)
	public int scoreOf(String word) //TESTED AND SEEMS TO BE WORKING
	{
		if(!this.dictionary.contains(word) || word.length() <= 2) {
			return 0;
		}
		

		int length = word.length();
		
		if(length >= 8) {
			return 11;
		}
		
		switch(length) {
		case 3: 
		case 4: return 1; 
		
		case 5: return 2;
		case 6: return 3;
		case 7: return 5;
		}
		
		return -1; //if -1 is return then something is wrong with the algorithm of this method
	}

	public static void main(String[] args) {
		
		
		System.out.println("WORKING");

		final String PATH   = "./data/";
		BoggleBoard  board  = new BoggleBoard(PATH + "board-q.txt");
		BoggleSolver solver = new BoggleSolver(PATH + "dictionary-algs4.txt");

		int totalPoints = 0;

		for (String s : solver.getAllValidWords(board)) {
			System.out.println(s + ", points = " + solver.scoreOf(s));
			totalPoints += solver.scoreOf(s);
		}

		System.out.println("Score = " + totalPoints); //should print 84

		
	}
	
}
