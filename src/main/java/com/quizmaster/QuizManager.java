package com.quizmaster;

import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

//this class has the logic of showing questions shuffled, loading and scoring
public class QuizManager {
    private List<Question> questions;
    private int score;

    public  QuizManager(){
        loadQuestionsFromFile();
        this.score = 0;
    }

    private void loadQuestionsFromFile(){
        try {
            Gson  gson = new Gson();
            Type questionListType = new TypeToken<List<Question>>(){}.getType();

            InputStream input = getClass().getClassLoader().getResourceAsStream("questions.json");
            if (input == null) {
                System.out.println("Could not find questions.");
                questions = Collections.emptyList();
                return;
            }

            Reader reader = new InputStreamReader(input);
            questions = gson.fromJson(reader, questionListType);
            Collections.shuffle(questions);
        } catch (Exception e) {
            System.out.println("Error loading questions: " + e.getMessage());
            questions = Collections.emptyList();
        }
    }

    public void startQuiz(){
        Scanner scanner = new Scanner(System.in);

        if (questions.isEmpty()){
            System.out.println("There are no questions!");
            return;
        }

        System.out.println("\nStarting the Quiz..");
        System.out.println("\nNOTE: If you enter invalid inputs and try to mess, the question will be skipped and you will get a zero for that question!");

        int questionNumber = 1;
        for (Question question : questions){
            System.out.println("\nQ" + questionNumber + ": " + question.getQuestion());
            List<String> options = question.getOptions();
            for(int i = 0; i < options.size(); i++){
                System.out.println((i + 1) + ". " + options.get(i));
            }

            System.out.println("Your choice of answer(1- " + options.size() + "): ");
            int userChoice = -1;
            try {
                userChoice = scanner.nextInt();
                scanner.nextLine(); // consume newline
            } catch (Exception e) {
                System.out.println("⚠️ Invalid input! Skipped...\n");
                scanner.nextLine(); // clear invalid input
            }

            if (userChoice >= 1 && userChoice <= options.size()) {
                String selected = options.get(userChoice - 1);
                if (selected.equalsIgnoreCase(question.getAnswer())) {
                    System.out.println("Your are Correct!\n");
                    score++;
                } else {
                    System.out.println("Your are Wrong, Correct answer is: " + question.getAnswer());
                }
            }else {
                System.out.println("Please enter a valid answer! Skipped...\n");
            }
            questionNumber++;
        }

        System.out.println("\nQuiz finished!\n");
        System.out.println("Your Score: " + score + "/" + questions.size());
        //feedback for the score
        if (score >= 4) {
            System.out.println("You're a quiz master! ");
        } else if (score >= 2) {
            System.out.println("Nice try! You’re getting there :) ");
        } else {
            System.out.println("Keep practicing champ!");
        }


    }
}
