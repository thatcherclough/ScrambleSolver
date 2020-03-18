package com.github.thatcherdev.scramblesolver;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScrambleSolver {

	private static String word = null;
	final private static String help = "ScrambleSolver: A simple word scramble solver (1.1.0)\n\nUsage:\n\tjava -jar scramblesolver.jar [-h] [-v] [-w WORD]\n\n"
			+ "Arguments:\n\t-h, --help\tDisplay this message.\n\t-v, --version\tDisplay current version.\n\t-w, --word\tSpecify word scramble to solve.";

	/**
	 * Starts ScrambleSolver.
	 * 
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		try {
			AnsiConsole.systemInstall();
			if (args.length == 0)
				throw new Exception();
			for (int k = 0; k < args.length; k++) {
				if (args[k].equals("-h") || args[k].equals("--help")) {
					throw new Exception();
				} else if (args[k].equals("-v") || args[k].equals("--version")) {
					System.out.println(help.substring(0, help.indexOf("\n")));
					System.exit(0);
				} else if (args[k].equals("-w") || args[k].equals("--word"))
					word = args[++k];
			}
			if (word == null)
				throw new Exception();

			try {
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
					System.out
							.println(Ansi.ansi().fgRed() + "\nCould not unscramble word :(" + Ansi.ansi().bgDefault());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println(help);
			System.exit(0);
		}
	}
}