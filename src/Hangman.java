import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Hangman {

	public static void main(String[] args) throws Exception {
		File dictionary = new File("D:\\Java\\Projects\\INE5625-hangman\\src\\dictionary.txt");
		
		Scanner textScanner = new Scanner(dictionary);
		Scanner input = new Scanner(System.in);
		
		ArrayList<String> words = new ArrayList<>();
		while(textScanner.hasNext()) {
			words.add(textScanner.nextLine());
		}
		String hidden_text = words.get((int)(Math.random() * words.size()));
		char[] textArray = hidden_text.toCharArray();
		
		char[] myAnswers = new char[textArray.length];
		for(int i = 0; i < textArray.length; i++) {
			myAnswers[i] = '?';
		}
		boolean finished = false;
		int lives = 6;
		
		while(finished == false) {
			System.out.print("=============");
			
			String letter = input.next();
			//Verifica se o que foi digitado é válido
			while(letter.length() != 1 || Character.isDigit(letter.charAt(0))) {
				System.out.println("Erro - Tente denovo");
				letter = input.next();
			}
			//verifica se a letra está na palavra
			boolean found = false;
			for(int i = 0; i< textArray.length; i++) {
				if(letter.charAt(0) == textArray[i]) {
					myAnswers[i] = textArray[i];
					found = true;
				}
			}
			if(!found) {
				lives--;
				
				System.out.println("Letra errada!");
			}
			
			boolean done = true;
			for(int i = 0; i < myAnswers.length; i++) {
				if(myAnswers[i] == '?') {
					System.out.print(" _");
					done = false;
				} else {
					System.out.print(" " + myAnswers[i]);
				}
			}
			System.out.println("\n" + "Vidas restantes: " + lives);
			drawHangman(lives);
			
			//verifica finalização do jogo
			if(done) {
				System.out.println("Parabéns!");
				finished = true;
			}
			if(lives <= 0) {
				System.out.println("Perdeu playboy!");
				finished = true;
			}
		}
		
		System.out.println(hidden_text);
		
	}
	
	public static void drawHangman(int l) {
		  if(l == 6) {
		   System.out.println("|----------");
		   System.out.println("|");
		   System.out.println("|");
		   System.out.println("|");
		   System.out.println("|");
		   System.out.println("|");
		   System.out.println("|");
		  }
		  else if(l == 5) {
		   System.out.println("|----------");
		   System.out.println("|    O");
		   System.out.println("|");
		   System.out.println("|");
		   System.out.println("|");
		   System.out.println("|");
		   System.out.println("|");
		  }
		  else if(l == 4) {
		   System.out.println("|----------");
		   System.out.println("|    O");
		   System.out.println("|    |");
		   System.out.println("|");
		   System.out.println("|");
		   System.out.println("|");
		   System.out.println("|");
		  }
		  else if(l == 3) {
		   System.out.println("|----------");
		   System.out.println("|    O");
		   System.out.println("|   -|");
		   System.out.println("|");
		   System.out.println("|");
		   System.out.println("|");
		   System.out.println("|");
		  }
		  else if(l == 2) {
		   System.out.println("|----------");
		   System.out.println("|    O");
		   System.out.println("|   -|-");
		   System.out.println("|");
		   System.out.println("|");
		   System.out.println("|");
		   System.out.println("|");
		  }
		  else if(l == 1) {
		   System.out.println("|----------");
		   System.out.println("|    O");
		   System.out.println("|   -|-");
		   System.out.println("|   /");
		   System.out.println("|");
		   System.out.println("|");
		   System.out.println("|");
		  }
		  else{
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


