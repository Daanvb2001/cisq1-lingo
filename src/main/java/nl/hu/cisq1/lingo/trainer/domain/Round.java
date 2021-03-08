package nl.hu.cisq1.lingo.trainer.domain;

import java.util.ArrayList;
import java.util.List;

public class Round {
    private List<Feedback> attempts;
    private String wordToGuess;



    public Round(String wordToGuess) {
        this.attempts = new ArrayList<>();
        this.wordToGuess = wordToGuess;
    }

//    public List<String> getAttempts() {
//        return attempts;
//    }

//    public void addFeedback(){
//        feedback.add(new Feedback(attempt,feedback.getWo))
//    }

//    public void addFeedback(){
//        attempts.;
//    }

    public List<Mark> fillMarks(String attempt){
        List<Mark> marks = new ArrayList<>();
        if (attempt.length()<wordToGuess.length()||attempt.length()>wordToGuess.length()){
            for (int i = 0; i < wordToGuess.length(); i++) {
                marks.add(Mark.INVALID);
            }
        }else {
            for (int i = 0; i < wordToGuess.length(); i++) {
                if (attempt.charAt(i)==wordToGuess.charAt(i)) {
                    marks.add(Mark.CORRECT);
                }else if(wordToGuess.contains(String.valueOf(attempt.charAt(i)))){
                    marks.add(Mark.PRESENT);
                }else if (!wordToGuess.contains(String.valueOf(attempt.charAt(i)))){
                    marks.add(Mark.ABSENT);
                }
            }
        }
        Feedback feedback = new Feedback(attempt, marks);
        this.attempts.add(feedback);
        return marks;
    }

    public void getFeedback(String attempt) {
        Feedback feedback = new Feedback(attempt, fillMarks(attempt));
        attempts.add(feedback);
    }
}

