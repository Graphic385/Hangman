public class Main {
	public static void main(String[] args) {
		printHangman(1);
		printKeyboard();
		printCategory("Example Category");
		printWord("_xampl_");
	}

	public static void printHangman(int i) {
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
		System.out.println(hangmanpics[i]);
	}

	public static void printKeyboard() {
		System.out.print("\033[s"); // save cursor position
		System.out.print("\033[3A"); // move up 3 lines
		System.out.print("\033[12G"); // move to column 12

		System.out.print("A B C D E F G H I J K L M\n");

		System.out.print("\033[12G"); // move to column 12
		System.out.print("N O P Q R S T U V W X Y Z");

		System.out.print("\033[u"); // restore cursor position
	}

	public static void printCategory(String categoryName) {
		System.out.print("\033[s"); // save cursor position
		System.out.print("\033[1m"); // bold
		System.out.print("\033[7A"); // move up 7 lines
		System.out.print("\033[12G"); // move to column 12
		System.out.print("\033[" + (13 - categoryName.length() / 2) + "C"); // move to right so text is centered

		System.out.print(categoryName);

		System.out.print("\033[22m"); // disable bold
		System.out.print("\033[u"); // restore cursor position
	}

	public static void printWord(String word) {
		System.out.print("\033[s"); // save cursor position
		System.out.print("\033[5A"); // move up 7 lines
		System.out.print("\033[12G"); // move to column 12
		System.out.print("\033[" + (13 - word.length() / 2) + "C"); // move to right so text is centered

		System.out.print(word);

		System.out.print("\033[u"); // restore cursor position
	}
}
