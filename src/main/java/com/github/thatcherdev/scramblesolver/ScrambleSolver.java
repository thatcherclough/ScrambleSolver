package com.github.thatcherdev.scramblesolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScrambleSolver {

	private static Scanner input = new Scanner(System.in);

	/**
	 * Starts ScrambleSolver.
	 * 
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		try {
			AnsiConsole.systemInstall();
			System.out.println("╔═╗┌─┐┬─┐┌─┐┌┬┐┌┐ ┬  ┌─┐╔═╗┌─┐┬ ┬  ┬┌─┐┬─┐\n"
					+ "╚═╗│  ├┬┘├─┤│││├┴┐│  ├┤ ╚═╗│ ││ └┐┌┘├┤ ├┬┘\n" + "╚═╝└─┘┴└─┴ ┴┴ ┴└─┘┴─┘└─┘╚═╝└─┘┴─┘└┘ └─┘┴└─");
			System.out.println("Enter a scramble to solve:");
			String word = getInput().toLowerCase();

			StringPermuter permuter = new StringPermuter();
			List<String> possibleWords = permuter.permute(word).stream().distinct().collect(Collectors.toList());

			Dictionary dictionary = new Dictionary();

			System.out.println("Checking " + possibleWords.size() + " possible words...");
			ArrayList<String> unscrambledWords = new ArrayList<String>();
			for (int k = 0; k < possibleWords.size(); k++) {
				int n = k;
				Thread thread = new Thread() {
					public void run() {
						String possibleWord = possibleWords.get(n);
						if (dictionary.contains(possibleWord))
							unscrambledWords.add(possibleWord);
					}
				};
				thread.start();
			}

			if (!unscrambledWords.isEmpty()) {
				System.out.println(Ansi.ansi().fgGreen() + "\nUnscrambled word(s):" + Ansi.ansi().fgDefault());
				for (String unscrambledWord : unscrambledWords)
					System.out.println(unscrambledWord);
			} else
				System.out.println(Ansi.ansi().fgRed() + "\nCould not unscramble word :(" + Ansi.ansi().bgDefault());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets user input.
	 *
	 * @return user input
	 */
	public static String getInput() {
		System.out.print(">");
		String ret = input.nextLine();
		if (ret.isEmpty())
			return getInput();
		else if (ret.contains(" ") || !ret.matches("[a-zA-Z]+")) {
			System.out.println("\nInvalid input\nEnter a scramble to solve:");
			return getInput();
		} else
			System.out.println();

		return ret;
	}
}