package com.quizmaster;

import java.util.List;

//this is a model class for each quiz question
public class Question {
    private String question;
    private String answer;
    private List<String> options;

    //constructor
    public Question(String question, List<String> options, String answer) {
        this.question = question;
        this.options = options;
        this.answer = answer;
    }

    //getters
    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public String getAnswer() {
        return answer;
    }

    //toString method for testing
    @Override
    public String toString() {
        return "Question: " + question + "\n Options: " +  options + "\n Answer: " + answer;
    }
}
