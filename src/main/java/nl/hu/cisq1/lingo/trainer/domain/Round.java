package nl.hu.cisq1.lingo.trainer.domain;

import lombok.Getter;
import nl.hu.cisq1.lingo.trainer.domain.exceptions.InvalidException;
import nl.hu.cisq1.lingo.trainer.domain.exceptions.NotPlayingException;

import java.util.ArrayList;
import java.util.List;

public class Round {
    private List<Feedback> history;
    private List<String> attempts;
    private String wordToGuess;
    private GameState state;

    public Round(String wordToGuess) {
        this.history = new ArrayList<>();
        this.attempts = new ArrayList<>();
        this.wordToGuess = wordToGuess;
        this.state= GameState.PLAYING;
        firsthint();
    }

    public String guess(String attempt){
        if (state != GameState.PLAYING){
            throw new NotPlayingException("Not in a game!");
        }
        String hint = "";
        Feedback feedback = new Feedback(attempt, wordToGuess);
        this.history.add(feedback);
        if (feedback.isWordGuessed()){
            state=GameState.WIN;
        } else if (attempts.size()==5){
            state=GameState.ELIMINATED;
        }
        hint=feedback.getHint(attempts.get(attempts.size()-1));
        this.attempts.add(hint);
        return hint;
    }

    public String firsthint(){
        String wordToGuessLength = ".";
        String hint= wordToGuess.charAt(0) + wordToGuessLength.repeat(wordToGuess.length()-1);
        this.attempts.add(hint);
        return hint;
    }

    public GameState getState() {
        return state;
    }

    public List<String> getAttempts() {
        return attempts;
    }
}

