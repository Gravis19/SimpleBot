package readability;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Scanner;
public class Main {
    static int countOfWords;
    static int countOfSentences;
    static int countOfChar;
    static int countOfSyllables;
    static int countOfPolysyllables;
    public static void main(String[] args) {

        String text = "";
        try (Scanner scanner = new Scanner(new File(args[0]))) {
            while (scanner.hasNext()) {
                text = text.concat(scanner.nextLine().concat(" "));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        String[] sentences = text.trim().split("[\\.\\!\\?]");
        countOfSentences = sentences.length;
        countOfChar = text.replaceAll("\\s+", "").length();
        for (String sentence : sentences) {
            String[] words = sentence.trim().split("\\s+");
            countOfWords += words.length;
            for (String word : words) {
                word = word.toLowerCase().trim();
                if (word.matches(".+e")) {
                    word = word.substring(0, word.length() - 1);
                }
                if (word.length() < 3) {
                    countOfSyllables++;
                } else {
                    int syllables = word.split("[aeiouy]+", -1).length - 1;
                    countOfSyllables += syllables;
                    if (syllables > 2) {
                        countOfPolysyllables++;
                    }
                }
            }
        }
        System.out.println("Words: " + countOfWords);
        System.out.println("Sentences: " + countOfSentences);
        System.out.println("Characters: " + countOfChar);
        System.out.println("Syllables: " + countOfSyllables);
        System.out.println("Polysyllables: " + countOfPolysyllables);
        System.out.println("Enter the score you want to calculate (ARI, FK, SMOG, CL, all):");
        Scanner scanner = new Scanner(System.in);
        countTheScore(scanner.next());

    }
    static int getAgeByScore(double score) {
        score = Math.round(score);
        int age = 0;
        switch ((int) score) {
            case 1:
                System.out.println("(about 6 year olds).");
                age = 6;
                break;
            case 2:
                System.out.println("(about 7 year olds).");
                age = 7;
                break;
            case 3:
                System.out.println("(about 9 year olds).");
                age = 9;
                break;
            case 4:
                System.out.println("(about 10 year olds).");
                age = 10;
                break;
            case 5:
                System.out.println("(about 11 year olds).");
                age = 11;
                break;
            case 6:
                age = 12;
                System.out.println("(about 12 year olds).");
                break;
            case 7:
                System.out.println("(about 13 year olds).");
                age = 13;
                break;
            case 8:
                System.out.println("(about 14 year olds).");
                age = 14;
                break;
            case 9:
                System.out.println("(about 15 year olds).");
                age = 15;
                break;
            case 10:
                System.out.println("(about 16 year olds).");
                age = 16;
                break;
            case 11:
                System.out.println("(about 17 year olds).");
                age = 17;
                break;
            case 12:
                System.out.println("(about 18 year olds).");
                age = 18;
                break;
            case 13:
                System.out.println("(about 24 year olds).");
                age = 24;
                break;
            case 14:
                System.out.println("(about 25+ year olds).");
                age = 25;
                break;
        }
        return age;
    }
    static void countTheScore(String rIndex) {
        double scoreARI = 4.71 * countOfChar / countOfWords + 0.5 * countOfWords / countOfSentences - 21.43;
        double scoreFK = 0.39 * countOfWords / countOfSentences + 11.8 * countOfSyllables / countOfWords - 15.59;
        double scoreSMOG = 1.043 * Math.sqrt(countOfPolysyllables * 30 / countOfSentences) + 3.1291;
        double scoreCL = 0.0588 * countOfChar / countOfWords * 100 - 0.296 * countOfSentences / countOfWords * 100 - 15.8;
        int ageARI = 0;
        int ageFK = 0;
        int ageSMOG = 0;
        int ageCL = 0;
        switch (rIndex.toLowerCase()) {
            case "ari":
                System.out.printf ("Automated Readability Index: %.2f ", scoreARI);
                getAgeByScore(scoreARI);
                break;
            case "fk":
                System.out.printf("Flesch–Kincaid readability tests: %.2f ", scoreFK);
                getAgeByScore(scoreFK);
                break;
            case "smog":
                System.out.printf("Simple Measure of Gobbledygook: %.2f ", scoreSMOG);
                getAgeByScore(scoreSMOG);
                break;
            case "cl":
                System.out.printf("Coleman–Liau index: %.2f ",scoreCL);
                getAgeByScore(scoreCL);
                break;
            case "all":
                System.out.printf ("Automated Readability Index: %.2f ", scoreARI);
                ageARI = getAgeByScore(scoreARI);
                System.out.printf("Flesch–Kincaid readability tests: %.2f", scoreFK);
                ageFK = getAgeByScore(scoreFK);
                System.out.printf("Simple Measure of Gobbledygook: %.2f ", scoreSMOG);
                ageSMOG = getAgeByScore(scoreSMOG);
                System.out.printf("Coleman–Liau index: %.2f ",scoreCL);
                ageCL = getAgeByScore(scoreCL);
                double averageAge = (ageARI + ageFK + ageSMOG + ageCL) / 4.0;
                System.out.printf("This text should be understood in average by %.2f year olds.\n", averageAge);
                break;
        }

    }
}
