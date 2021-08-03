import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Hangman {

    private char[] stream; // dado recebido/enviado
	private String word;
	private boolean finished = false;
	private int lives = 6;
	private char[] myAnswers = null;

	public Hangman() {
		initGame();		
		System.out.println(this.word);
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

		// seleciona a palavra
		this.word = words.get((int) (Math.random() * words.size()));
		char[] textArray = this.word.toCharArray();
		myAnswers = new char[textArray.length];

		for (int i = 0; i < textArray.length; i++) {
			myAnswers[i] = '-';
		}
		this.stream = myAnswers; // seta a palavra nesse formato - - - - -
	}

    public void setStream(char[] stream) {
        this.stream = stream;
		handleStream();
    }

    public char[] getStream() {
        return this.stream;
    }

	/**
	 * trata se a letra enviada pelo client esta na palavra
	 */
	private void handleStream() {
		// verificar se a letra esta na palavra
		boolean found = false;

		for (int i = 0; i < this.word.length(); i++) {
			if (this.word.charAt(i) == this.stream[0]) {
				// deve virar ---A--A
				this.myAnswers[i] = this.stream[0];
				found = true;
			}
		}

		if (!found) {
			this.lives = this.lives - 1; // decrementa 1 pt de vida
			if(this.lives == 0) {
				this.finished = true;
			}
		}
		this.stream = myAnswers;
	}
}
