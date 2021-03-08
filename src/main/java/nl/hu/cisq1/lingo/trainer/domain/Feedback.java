package nl.hu.cisq1.lingo.trainer.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Feedback {
    private List<Mark> marks;
    private String attempt;

    public Feedback(String attempt, List<Mark> marks) {
        this.marks = marks;
        this.attempt = attempt;
    }

    public boolean isWordGuessed() {
        return this.marks.stream().allMatch(mark -> mark.equals(Mark.CORRECT));
    }

    public boolean isWordValid() {
        return this.marks.stream().allMatch(Mark.INVALID::equals);
    }


    public String giveHint(String previousHint, String wordToGuess){
        String hint = "";
            for (int i = 0; i < wordToGuess.length(); i++) {
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

    @Override
    public String toString() {
        return "Feedback{" +
                "marks=" + marks +
                '}';
    }
}
