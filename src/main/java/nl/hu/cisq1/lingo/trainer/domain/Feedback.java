package nl.hu.cisq1.lingo.trainer.domain;

import lombok.ToString;
import nl.hu.cisq1.lingo.trainer.domain.exceptions.InvalidException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ToString
public class Feedback {
    private List<Mark> marks;
    private String attempt;
    private String wordToGuess;

    public Feedback(String attempt,String wordToGuess ) {
        this.wordToGuess=wordToGuess;
        this.attempt = attempt;
        this.marks=fillMarks();
    }

    public boolean isWordGuessed() {
        return this.marks.stream().allMatch(mark -> mark.equals(Mark.CORRECT));
    }

    public boolean isWordValid() {
            throw new InvalidException("Guess is invalid");
    }

    public List<Mark> fillMarks() throws InvalidException {
        List<Mark> marks = new ArrayList<>();
        if (attempt.length()!=wordToGuess.length()){
            isWordValid();
        }else if(wordToGuess.equals(attempt)){
            for (int i = 0; i < wordToGuess.length(); i++) {
                marks.add(Mark.CORRECT);
            }
        }else{
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
        return marks;
    }

    public String getHint(String previousHint){
        String hint = "";
        for (int i = 0; i < attempt.length(); i++) {
                if (marks.get(i) == Mark.CORRECT) {
                    hint += this.attempt.charAt(i);
                }else if (!String.valueOf(previousHint.charAt(i)).equals(".")) {
                    hint += previousHint.charAt(i);
                } else {
                    hint += ".";
            }
        }
        return hint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return Objects.equals(marks, feedback.marks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(marks);
    }

}
