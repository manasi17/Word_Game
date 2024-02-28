import java.util.*;

public class WordGameImpl {
	static boolean win = false;

	public static void main(String[] args) {
			    
	    List<String> wordList = new ArrayList<String>();
	    wordList.add("apple");
	    wordList.add("dog");
	    wordList.add("cat");
	    wordList.add("book");
	    
	    
	    
	    
	    String correctWord = pickAWord(wordList);
	    System.out.println("Picked word: " + correctWord); 

	    startGuess(correctWord);
	}

	public static void startGuess(String correctWord) {
		int maxLives = 5;
		int sizeOfAnswer = correctWord.length();
		String blankAnswer = "";
		for(int i=0; i<sizeOfAnswer; i++) {
			blankAnswer = blankAnswer.concat("*");
		}
		Scanner myObj = new Scanner(System.in);
	    System.out.println("Guess the word:" + blankAnswer);
	    
	    String response = myObj.nextLine();
	    
	    readResponseAndFeedback(response,correctWord, maxLives);
	    
	}

	public static void readResponseAndFeedback(String response, String correctWord, int maxLives) {
		//String feedback = "";
		while(maxLives > 0) {
			if(win == true ) {
				System.out.println("You have won the game. The word was :" + correctWord);
				break;
		    }
			if(correctWord.contains(response)) {
				String guess = maskRemainingCharacters(response, correctWord);
				System.out.println("Correct :" + guess);
				Scanner myObj = new Scanner(System.in);
				response = myObj.nextLine();
			}
			else {
				maxLives--;
				System.out.println("Incorrect. 1 life lost, lives remaining : " + maxLives);
				Scanner myObj = new Scanner(System.in);
				response = myObj.nextLine();
			}
		}
	}

	public static String maskRemainingCharacters(String response, String correctWord) {
		char responseChar = response.charAt(0);
		for(int i=0; i<correctWord.length();i++) {
			if(correctWord.charAt(i) != responseChar) {
				correctWord = correctWord.substring(0,i) + "*" + correctWord.substring(i+1);
			}
		}
		if(!correctWord.contains("*")) {
			win =true;
		}
		return correctWord;
	}

	public static String pickAWord(List<String> wordList) {
		Random generator = new Random();
		int randomIndex = generator.nextInt(wordList.size());
		return wordList.get(randomIndex);
	}

}
