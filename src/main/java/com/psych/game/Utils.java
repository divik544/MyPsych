package com.psych.game;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    private static List<String> wordsList;
    static {
        wordsList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("words.txt"));
            String word;
            do {
                word = br.readLine();
                wordsList.add(word);
            }while(word != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Pair<String, String>> readQAFile(String filename) {

        String question, answer;
        List<Pair<String, String>> question_answers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            do {
                question = br.readLine();
                answer = br.readLine();
                if (question == null || answer == null || question.length() > Constants.MAX_QUESTION_LENGTH || answer.length() > Constants.MAX_ANSWER_LENGTH) {
                    System.out.println("Skipping Question : " + question);
                    System.out.println("Skipping Answer : " + answer);
                }
                question_answers.add(new Pair<>(question, answer));

            } while (question != null && answer != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return question_answers;

    }

    public static String getSecretCodeById(Long id) {

        int base = wordsList.size();
        String code = "";
        while(id > 0) {
            code += wordsList.get((int)(id%base)) + " ";
            id /= base;
        }
        return code;
    }
}
