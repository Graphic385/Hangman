import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
	public static List<Character> guessedChars = new ArrayList<Character>();
	public static String categoryName;
	public static String wordToGuess;
	public static int numIncorrectGuesses;
	public static boolean guessedWord;
	public static String guessedWordString;

	public static void main(String[] args) {

		startGame();
	}

	public static void startGame() {
		// TODO tell them how to play the game
		System.out.println("");
		try {
			List<File> files = Files.list(Path.of("./assets/wordlist"))
					.filter(path -> !Files.isDirectory(path))
					.map(path -> path.toFile())
					.collect(Collectors.toList());

			Random rand = new Random();
			File randomFile = files.get(rand.nextInt(files.size()));
			categoryName = randomFile.getName();

			Scanner scanner = new Scanner(randomFile);
			List<String> lines = new ArrayList<String>();
			@SuppressWarnings("unused")
			int count = 0;
			while (scanner.hasNextLine()) {
				lines.add(scanner.nextLine());
				count++;
			}
			wordToGuess = lines.get(rand.nextInt(lines.size())).toLowerCase();
			numIncorrectGuesses = 0;
			guessedWord = false;
			guessedWordString = "";
			scanner.close(); 
		} catch (Exception err) { 
			System.err.println(err); 
		}
		
		Scanner sc = new Scanner(System.in);

		while (true) {
			printGame();
			char letter;
			if (!checkLoseCondition()) {
				System.out.print("Guess a letter or word: ");
				String currentGuess = sc.next();
				if (currentGuess.length() == 1) {
					letter = currentGuess.toLowerCase().charAt(0);
					guessedChars.add(letter);
					if (!checkLetter(letter))
						numIncorrectGuesses++;
				} else if (currentGuess.length() == wordToGuess.length()) {
					guessedWord = true;
					guessedWordString = currentGuess.toLowerCase();
					for (char character : guessedWordString.toCharArray()) {
						guessedChars.add(character);
					}

				} else {
					System.out.println("Your guess was invaild.");
				}
			} else if (checkLoseCondition()) {
				System.out.print("You Lost. Type Y to play again or N to quit game: ");
				if (sc.next().toLowerCase().charAt(0) == 'y') {
					guessedChars.clear();
					startGame();
				}
				break;

			}

			if (checkWinCondition()) {
				printGame();
				System.out.print("You win!!! Type Y to play again or N to quit game: ");
				if (sc.next().toLowerCase().charAt(0) == 'y') {
					guessedChars.clear();
					startGame();
				}
				break;
			}
		}
		sc.close();
	}

	public static void printGame() {
		printHangman();
		printKeyboard();
		printCategory();
		printwordToGuess();
	}

	public static void printHangman() {
		String[] hangmanpics = {
						"   +---+\n" +
						"   |   |\n" +
						"       |\n" +
						"       |\n" +
						"       |\n" +
						"       |\n" +
						" =========",

						"   +---+\n" +
						"   |   |\n" +
						"   O   |\n" +
						"       |\n" +
						"       |\n" +
						"       |\n" +
						" =========",

						"   +---+\n" +
						"   |   |\n" +
						"   O   |\n" +
						"   |   |\n" +
						"       |\n" +
						"       |\n" +
						" =========",

						"   +---+\n" +
						"   |   |\n" +
						"   O   |\n" +
						"  /|   |\n" +
						"       |\n" +
						"       |\n" +
						" =========",

						"   +---+\n" +
						"   |   |\n" +
						"   O   |\n" +
						"  /|\\  |\n" +
						"       |\n" +
						"       |\n" +
						" =========",

						"   +---+\n" +
						"   |   |\n" +
						"   O   |\n" +
						"  /|\\  |\n" +
						"  /    |\n" +
						"       |\n" +
						" =========",

						"   +---+\n" +
						"   |   |\n" +
						"   O   |\n" +
						"  /|\\  |\n" +
						"  / \\  |\n" +
						"       |\n" +
						" ========="
		};
		System.out.println(hangmanpics[numIncorrectGuesses]);
	}

	public static void printKeyboard() {
		char[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

		System.out.print("\033[s"); // save cursor position
		System.out.print("\033[3A"); // move up 3 lines
		System.out.print("\033[12G"); // move to column 12

		for (int i = 0; i < alphabet.length; i++) {
			boolean isGuessed = false;
			for (char letter : guessedChars) {
				if (letter == alphabet[i]) {
					isGuessed = true;
				}
			}
			if (isGuessed) {
				System.out.print("\033[2m");
				System.out.print(alphabet[i] + " ");
				System.out.print("\033[22m");
			} else {
				System.out.print(alphabet[i] + " ");
			}

			if (i == 12)
				System.out.print("\n\033[12G"); // move to column 12
		}

		System.out.print("\033[u"); // restore cursor position
	}

	public static void printCategory() {
		System.out.print("\033[s"); // save cursor position
		System.out.print("\033[1m"); // bold
		System.out.print("\033[7A"); // move up 7 lines
		System.out.print("\033[12G"); // move to column 12
		System.out.print("\033[" + (13 - categoryName.length() / 2) + "C"); // move to right so text is centered

		System.out.print(categoryName);

		System.out.print("\033[22m"); // disable bold
		System.out.print("\033[u"); // restore cursor position
	}

	public static void printwordToGuess() {
		System.out.print("\033[s"); // save cursor position
		System.out.print("\033[5A"); // move up 7 lines
		System.out.print("\033[12G"); // move to column 12
		System.out.print("\033[" + (13 - wordToGuess.length() / 2) + "C"); // move to right so text is centered

		char[] wordChars = wordToGuess.toLowerCase().toCharArray();
		for (char character : wordChars) {
			if (checkIfInGussedChars(character) && !checkLoseCondition()) {
				System.out.print(character);
			} else if (checkLoseCondition()) {
				System.out.print(character);
			} else {
				System.out.print("_");

			}
		}

		System.out.print("\033[u"); // restore cursor position
	}

	// checks if the passed through char is in the gussed chars return true
	public static boolean checkIfInGussedChars(char wordChar) {
		for (char character : guessedChars) {
			if (character == wordChar)
				return true;
		}

		return false;
	}

	public static boolean checkLetter(char letter) {
		char[] wordChars = wordToGuess.toLowerCase().toCharArray();
		for (char character : wordChars) {
			if (character == letter)
				return true;
		}

		return false;
	}

	public static boolean checkWinCondition() {
		char[] wordGuessChars = wordToGuess.toLowerCase().toCharArray();

		if (!guessedWord) {
			int counter = 0;
			for (char character : wordGuessChars) {
				if (checkIfInGussedChars(character))
					counter++;
				if (counter == wordToGuess.length())
					return true;
			}
		} else {
			char[] gussedWordChars = guessedWordString.toCharArray();
			for (int i = 0; i < guessedWordString.length(); i++) {
				if (gussedWordChars[i] != wordGuessChars[i])
					return false;
			}
			return true;

		}
		return false;
	}

	public static boolean checkLoseCondition() {
		if (numIncorrectGuesses >= 6)
			return true;
		if (guessedWord && !checkWinCondition())
			return true;
		return false;
	}
}
