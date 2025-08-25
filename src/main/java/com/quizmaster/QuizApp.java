package com.quizmaster;

import java.util.Scanner;

public class QuizApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean retry;

        System.out.println("\n*** Welcome to Quiz Master ***");

        do {
            QuizManager manager = new QuizManager();
            manager.startQuiz();

            // Asks user if they want to retry
            System.out.print("\nDo you want to retry the quiz? (yes/no): ");
            String response = scanner.nextLine().trim().toLowerCase();

            retry = response.equals("yes") || response.equals("y") || response.equals("yea");

        } while (retry);

        System.out.println("Thanks for playing! Bye Bye...");
        scanner.close();
    }
}
