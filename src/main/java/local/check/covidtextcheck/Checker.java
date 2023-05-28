package local.check.covidtextcheck;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Checker {
    private static List<String> goodWords;
    private static List<String> badWords;

    public static void initWordArrays() {
        goodWords = new ArrayList<>();
        badWords = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File("./GOOD.txt"));
            while (scanner.hasNextLine()) goodWords.add(scanner.next());
            scanner = new Scanner(new File("./BAD.txt"));
            while (scanner.hasNextLine()) badWords.add(scanner.next());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static List<Integer> getAmountOfWords(String text) {
        List<Integer> result = new ArrayList<>();
        int amountOfGoodWords = 0, amountOfBadWords = 0;
        for (String goodWord : goodWords) amountOfGoodWords += countOccurrences(text, goodWord);
        for (String badWord : badWords) amountOfBadWords += countOccurrences(text, badWord);
        result.add(amountOfGoodWords);
        result.add(amountOfBadWords);
        return result;
    }

    private static int countOccurrences(String text, String word) {
        return (text.length() - text.replaceAll(word, "").length()) / word.length();
    }
}
