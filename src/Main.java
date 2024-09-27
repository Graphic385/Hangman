public class Main {

    public static void main(String[] args) {
	printHangman(1);
	printKeyboard();
	
    }
    
    public static void printHangman(int i) {
	String[] hangmanpics = {
		"""
		  +---+
		  |   |
		      |
		      |
		      |
		      |
		=========""",
		"""
		  +---+
		  |   |
		  O   |
		      |
		      |
		      |
		=========""",
		"""
		  +---+
		  |   |
		  O   |
		  |   |
		      |
		      |
		=========""",
		"""
		  +---+
		  |   |
		  O   |
		 /|   |
		      |
		      |
		=========""",
		"""
		  +---+
		  |   |
		  O   |
		 /|\\  |
		      |
		      |
		=========""",
		"""
		  +---+
		  |   |
		  O   |
		 /|\\  |
		 /    |
		      |
		=========""",
		"""
		  +---+
		  |   |
		  O   |
		 /|\\  |
		 / \\  |
		      |
		=========""" };
	System.out.println(hangmanpics[i]);
    }
    
    public static void printKeyboard() {
	System.out.print("\033[0;0H");
	System.out.println("A B C D E F G H I J K L M\n"
			 + "N O P Q R S T U V W X Y Z");
    }
}
