import java.lang.reflect.Array;
import java.util.*;
import javax.swing.text.Position;

public class Hangman {

	public Scanner scanner = new Scanner(System.in);
	public String userInput;
	public String randomWord;
	public int userTries = 6;
	public ArrayList<String> wordBank = new ArrayList<String>();
	public ArrayList<String> wordToGuess = new ArrayList<String>();
	public ArrayList<String> underScoresOfRandomWord = new ArrayList<String>();
	public ArrayList<String> guessedLetters = new ArrayList<String>();
	public boolean getWord = true;
	public boolean userIsGuessing = false;
	public boolean putWord = false;
	public boolean printing = true;
	public boolean gameIsStarting = true;
	public boolean gameIsEnding = true;

	public Hangman() {
		wordBank.add("Apple");
		wordBank.add("House");
		wordBank.add("Dog");
		wordBank.add("Runner");
		wordBank.add("Tight");
		wordBank.add("Hugger");
		wordBank.add("Log");
		wordBank.add("Pulley");
		wordBank.add("Dye");
		wordBank.add("Lung");
		startTheGame();
	}

	public void startTheGame() {
		//addWordsToWordBank();
		while (gameIsStarting == true) {
			System.out
					.println("Would you like to start the game? (Y)es, (N)o? Would you like to (A)dd a word to the wordbank?");
			userInput = scanner.nextLine();
			if (userInput.equalsIgnoreCase("Y")) {
				System.out.println("Okay:");
				userIsGuessing = true;
				gameIsStarting = false;
				assignRandomWord();
			} else if (userInput.equalsIgnoreCase("N")) {
				System.out.println("Thank You");
				gameIsStarting = false;
			} else {
				System.out.println("Please enter a valid command");
			}
		}
	} 
	
	
	public void assignRandomWord() {
		putWord = true;
		int x = 0;
		for (int i = 0; i < wordBank.size(); i++) {
			x += 1;
		}
		System.out.println(x);
		int randomNumber = (int) (Math.random() * x);
		randomWord = wordBank.get(randomNumber);
		assignWordToArrayList();
	}

	public void assignWordToArrayList() {

		while (getWord == true) {
			for (int i = 0; i < randomWord.length(); i++) {
				wordToGuess.add(randomWord.substring(i, i + 1));
			}
			getWord = false;
		}
		addSpacesOfRandomWord();
	}

	public void addSpacesOfRandomWord() {

		int x = randomWord.length();
		for (int i = 0; i < x; i++) {
			underScoresOfRandomWord.add("_");
		}
		while (putWord == true) {
			for (int i = 0; i < underScoresOfRandomWord.size(); i++) {
				System.out.print(underScoresOfRandomWord.get(i));
			}
			putWord = false;
		}
		userIsGuessing = true;
		checkUserInput();
	}

	public void checkUserInput() {
		while (userIsGuessing == true) {

			userInput = scanner.nextLine();
			for (int i = 0; i < wordToGuess.size(); i++) {
				if (wordToGuess.contains(userInput)) {
					addToGuessedLetters();
					swapLetterForUnderScore();
					swapUnderScoreForLetter();
					printing = false;
				} else if (!(wordToGuess.contains(userInput))
						& !(guessedLetters.contains(userInput))) {
					addToGuessedLetters();
					printOutWords("Wrong! Try again!");
					userTries = userTries - 1;
				} else if (guessedLetters.contains(userInput)) {
					printOutWords("You already guessed that");
				}
			}
			printing = true;

			for (int i = 0; i < underScoresOfRandomWord.size(); i++) {
				System.out.print(underScoresOfRandomWord.get(i));
			}
			if (!(underScoresOfRandomWord.contains("_"))) {
				endGameOrRestart();
			} else if (userTries == 0) {
				endGameOrRestart();
			}
		}
	}

	public void endGameOrRestart() {
		while (gameIsEnding == true) {
			System.out.println("\nWould you like to play again? (Y)es, (N)o?");
			userInput = scanner.nextLine();
			if (userInput.equalsIgnoreCase("y")) {
				clearArrayLists();
				setBooleans();
				setUserTriesBack();
				assignRandomWord();
			} else if (userInput.equalsIgnoreCase("n")) {
				System.out.println("Ok, have a good day!");
				gameIsEnding = false;
				end();
			} else {
				System.out.println("Please enter a valid command.");
			}
		}
	}
	
	public void end() {
		gameIsStarting = false;
		gameIsEnding = false;
		userIsGuessing = false;
	}

	public void setUserTriesBack() {
		userTries = 6;
	}

	public void printOutWords(String words) {
		while (printing == true) {
			System.out.println(words);
			printing = false;
		}
	}

	public void swapLetterForUnderScore() {

		underScoresOfRandomWord.set(wordToGuess.indexOf(userInput), userInput);
	}

	public void swapUnderScoreForLetter() {

		for (int i = 0; i < wordToGuess.size(); i++) {
			wordToGuess.set(underScoresOfRandomWord.indexOf(userInput), "_");
		}
	}

	public void addToGuessedLetters() {

		guessedLetters.add(userInput);
	}

	public void clearArrayLists() {
		wordToGuess = new ArrayList<String>();
		underScoresOfRandomWord = new ArrayList<String>();
		guessedLetters = new ArrayList<String>();
	}

	public void setBooleans() {
		getWord = true;
		userIsGuessing = false;
		putWord = false;
		printing = true;
		gameIsStarting = true;
		gameIsEnding = true;
	}

	public static void main(String[] args) {
		
		Hangman test = new Hangman();

	}
}