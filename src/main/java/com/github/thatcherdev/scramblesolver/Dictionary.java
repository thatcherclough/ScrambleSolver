package com.github.thatcherdev.scramblesolver;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Dictionary {

    private ArrayList<String> words = null;

    /**
     * Constructs a new Dictionary.
     * <p>
     * If a file with the name 'dictionary.txt' already exists, adds each line from
     * 'dictionary.txt' to {@link #words}. Otherwise, downloads a text file
     * containing the 479k most common words in the english language from
     * {@link url} to 'dictionary.txt' and adds each line to {@link #words}.
     * 
     * @throws IOException
     */
    public Dictionary() throws IOException {
        words = new ArrayList<String>();

        File dictionary = new File("dictionary.txt");
        if (dictionary.exists()) {
            Scanner in = new Scanner(dictionary);
            while (in.hasNextLine())
                words.add(in.nextLine());
            in.close();
        } else {
            System.out.println("Downloading dictionary...");
            URL url = new URL("https://raw.githubusercontent.com/dwyl/english-words/master/words.txt");
            Scanner in = new Scanner(url.openStream());
            PrintWriter out = new PrintWriter(dictionary);
            while (in.hasNextLine()) {
                String line = in.nextLine().toLowerCase();
                words.add(line);
                out.println(line);
            }
            in.close();
            out.flush();
            out.close();
        }
    }

    /**
     * If String {@link word} is an item in ArrayList<String> {@link #words}.
     * 
     * @param word
     * @return boolean if {@link word} is an item in {@link #words}.
     */
    public boolean contains(String word) {
        return words.contains(word);
    }
}