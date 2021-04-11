package nl.hu.cisq1.lingo.trainer.domain;

import lombok.ToString;
import nl.hu.cisq1.lingo.trainer.domain.exceptions.InvalidException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ToString
@Entity
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ElementCollection
    private List<Mark> marks;
    private String attempt;
    private String wordToGuess;

    public Feedback() {
    }

    public Feedback(String attempt, String wordToGuess ) {
        this.wordToGuess=wordToGuess;
        this.attempt = attempt;
        this.marks=fillMarks();
    }

    public boolean isWordGuessed() {
        return this.marks.stream().allMatch(mark -> mark.equals(Mark.CORRECT));
    }


    public List<Mark> fillMarks() throws InvalidException {
        List<Mark> marksList = new ArrayList<>();
        if (attempt.length()!=wordToGuess.length()){
            throw new InvalidException("guess is invalid");
        }else if(wordToGuess.equals(attempt)){
            for (int i = 0; i < wordToGuess.length(); i++) {
                marksList.add(Mark.CORRECT);
            }
        }else{
            for (int i = 0; i < wordToGuess.length(); i++) {
                if (attempt.charAt(i)==wordToGuess.charAt(i)) {
                    marksList.add(Mark.CORRECT);
                }else if(wordToGuess.contains(String.valueOf(attempt.charAt(i)))){
                    marksList.add(Mark.PRESENT);
                }else if (!wordToGuess.contains(String.valueOf(attempt.charAt(i)))){
                    marksList.add(Mark.ABSENT);
                }
            }
        }
        return marksList;
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
