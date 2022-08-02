package com.preston.examsoft;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class WordCount {


    public static void main(String[] args) throws Exception {
        String input = "the cat is in the bag";
        input = getResourceFileAsString("input.txt");
        countWords(input);
    }

    private static void countWords(String input) {
        Map<String, Integer> wordCounts = new HashMap<>();
        String[] words = input.split("\\s+"); // should be any whitespace

        // could also be done with this, below, but I believe the one above is more robust, handles more cases
//        StringTokenizer st = new StringTokenizer(input, " ");
//        while(st.hasMoreTokens()) {
//        }

        for(String word : words) {
            Integer count = wordCounts.get(word);
            if(count == null) {
                count = new Integer(1);
            } else {
                count = new Integer(count.intValue() + 1);
            }
            wordCounts.put(word, count);
        }

        // now sort
        wordCounts.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(entry -> System.out.println( String.format("%s %s", entry.getValue(), entry.getKey()) ));

    }

    /**
     * Reads given resource file as a string.
     *
     * @param fileName path to the resource file
     * @return the file's contents
     * @throws IOException if read fails for any reason
     */
    static String getResourceFileAsString(String fileName) throws IOException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        try (InputStream is = classLoader.getResourceAsStream(fileName)) {
            if (is == null) return null;
            try (InputStreamReader isr = new InputStreamReader(is);
                 BufferedReader reader = new BufferedReader(isr)) {
                return reader.lines().collect(Collectors.joining(System.lineSeparator()));
            }
        }
    }
}
