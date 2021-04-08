package nl.hu.cisq1.lingo.trainer.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Progress {
    private Long id;
    private GameState gameState;
    private String wordToGuess;
    private int score;
    private String hint;
    private List<Feedback> history;

}
