import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Hangman {

    private char[] stream; // dado recebido/enviado
	private String word;
	private static boolean finished = false;
	private static int lives = 6;
	private char[] myAnswers = null;

	public Hangman() {
		
		initGame();
		this.word = myAnswers.toString();
		this.setStream(myAnswers); // seta a palavra _ _ _ _ _
	}

	private void initGame() {
		URL songPath = Hangman.class.getResource("dictionary.txt");
		Scanner textScanner = null;

		try {
			File file = new File(songPath.toURI());
			textScanner = new Scanner(file);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		ArrayList<String> words = new ArrayList<>();

		while (textScanner.hasNext()) {
			words.add(textScanner.nextLine());
		}

		String hidden_text = words.get((int) (Math.random() * words.size()));
		char[] textArray = hidden_text.toCharArray();
		myAnswers = new char[textArray.length];

		for (int i = 0; i < textArray.length; i++) {
			myAnswers[i] = '_';
		}
	}

    public void setStream(char[] stream) {
		System.out.println("aquiii");
		System.out.println(stream);
        this.stream = stream;
    }

    public char[] getStream() {
        return this.stream;
    }

	/**
	 * trata se a letra enviada pelo client esta na palavra
	 */
	private void handleStream() {
		// String letter = this.getStream();
		boolean find = false;

	}

	// public static void main(String[] args) throws Exception {
		

	// 	while (finished == false) {
	// 		System.out.print("=============");

	// 		String letter = input.next();
	// 		// Verifica se o que foi digitado � v�lido
	// 		while (letter.length() != 1 || Character.isDigit(letter.charAt(0))) {
	// 			System.out.println("Erro - Tente denovo");
	// 			letter = input.next();
	// 		}
	// 		// verifica se a letra est� na palavra
	// 		boolean found = false;
	// 		for (int i = 0; i < textArray.length; i++) {
	// 			if (letter.charAt(0) == textArray[i]) {
	// 				myAnswers[i] = textArray[i];
	// 				found = true;
	// 			}
	// 		}
	// 		if (!found) {
	// 			lives--;

	// 			System.out.println("Letra errada!");
	// 		}

	// 		boolean done = true;
	// 		for (int i = 0; i < myAnswers.length; i++) {
	// 			if (myAnswers[i] == '?') {
	// 				System.out.print(" _");
	// 				done = false;
	// 			} else {
	// 				System.out.print(" " + myAnswers[i]);
	// 			}
	// 		}
	// 		System.out.println("\n" + "Vidas restantes: " + lives);
	// 		drawHangman(lives);

	// 		// verifica finaliza��o do jogo
	// 		if (done) {
	// 			System.out.println("Parab�ns!");
	// 			finished = true;
	// 		}
	// 		if (lives <= 0) {
	// 			System.out.println("Perdeu playboy!");
	// 			finished = true;
	// 		}
	// 	}

	// 	System.out.println(hidden_text);

	// }

	public static void drawHangman(int l) {
		if (l == 6) {
			System.out.println("|----------");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
		} else if (l == 5) {
			System.out.println("|----------");
			System.out.println("|    O");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
		} else if (l == 4) {
			System.out.println("|----------");
			System.out.println("|    O");
			System.out.println("|    |");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
		} else if (l == 3) {
			System.out.println("|----------");
			System.out.println("|    O");
			System.out.println("|   -|");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
		} else if (l == 2) {
			System.out.println("|----------");
			System.out.println("|    O");
			System.out.println("|   -|-");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
		} else if (l == 1) {
			System.out.println("|----------");
			System.out.println("|    O");
			System.out.println("|   -|-");
			System.out.println("|   /");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
		} else {
			System.out.println("|----------");
			System.out.println("|    O");
			System.out.println("|   -|-");
			System.out.println("|   /|");
			System.out.println("|");
			System.out.println("|");
			System.out.println("|");
		}
	}
}
