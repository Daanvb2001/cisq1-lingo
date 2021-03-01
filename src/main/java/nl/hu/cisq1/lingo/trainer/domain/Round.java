package nl.hu.cisq1.lingo.trainer.domain;

import java.util.ArrayList;
import java.util.List;

public class Round {
//    private List<Feedback> feedback;
    private String wordToGuess;
    private List<String> attempts;

    public Round(String wordToGuess) {
        this.wordToGuess = wordToGuess;
        this.attempts = new ArrayList<>();
//        this.feedback = new ArrayList<>();
    }

    public String getWordToGuess() {
        return wordToGuess;
    }

    public List<String> getAttempts() {
        return attempts;
    }

//    public List<Feedback> getFeedback() {
//        return feedback;
//    }
}

