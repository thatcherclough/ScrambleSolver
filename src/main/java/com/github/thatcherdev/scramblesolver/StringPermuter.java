package com.github.thatcherdev.scramblesolver;

import java.util.ArrayList;

public class StringPermuter {

    private static ArrayList<String> permutations = null;

    /**
     * Constructs a new StringPermuter.
     */
    public StringPermuter() {
        permutations = new ArrayList<String>();
    }

    /**
     * Permutes String {@link string} to get all possible letter combinations.
     * 
     * @param string String to permute
     * @return ArrayList<String> all possible letter combinations.
     */
    public ArrayList<String> permute(String string) {
        permute("", string);
        return permutations;
    }

    /**
     * Permutes with prefix {@link prefix} and String {@link string}.
     * 
     * @param prefix prefix
     * @param string String
     */
    private void permute(String prefix, String string) {
        int n = string.length();

        if (n == 0)
            permutations.add(prefix);
        else
            for (int k = 0; k < n; k++)
                permute(prefix + string.charAt(k), string.substring(0, k) + string.substring(k + 1, n));
    }
}