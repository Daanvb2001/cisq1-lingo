package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exceptions.AlreadyPlayingException;
import nl.hu.cisq1.lingo.trainer.domain.exceptions.NoGameActiveException;
import nl.hu.cisq1.lingo.trainer.domain.exceptions.WrongWordLengthException;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int score;
    private int wordLength;

    @OneToMany
    @Cascade(CascadeType.ALL)
    private final List<Round> rounds;

    public Game() {
        this.score = 0;
        this.rounds=new ArrayList<>();
        this.wordLength=5;
    }

    public Progress startRound(String wordToGuess){
        if (wordToGuess.length()!=wordLength){
            throw new WrongWordLengthException("Word has the wrong length!");
        }
        if (!rounds.isEmpty() && this.rounds.get(rounds.size()-1).getState()==GameState.PLAYING){
            throw new AlreadyPlayingException("There is a game still active!");
        }
        Round round = new Round(wordToGuess);
        this.rounds.add(round);
        amountOfCharacters();

        return getProgress();
    }

    public Progress guess(String attempt){
        if (rounds.isEmpty()){
            throw new NoGameActiveException("There is no active game");
        }
        Round round = rounds.get(rounds.size()-1);
        round.guess(attempt);

        return getProgress();
    }

    public int amountOfCharacters(){
        if (wordLength==5){
            wordLength=6;
        } else if (wordLength==6){
            wordLength=7;
        } else if (wordLength==7){
            wordLength=5;
        }
        return wordLength;
    }

    public int playerScore(){
        int tempscore = 0;
        for (Round round: rounds){
            if (round.getState()==GameState.WIN){
                int amountOfAttempts = round.getAttempts().size()-1;
                tempscore+=5*(5-amountOfAttempts)+5;
            }
        }
        score=tempscore;
        return score;
    }

    public Round getRound() {
        if (rounds.isEmpty()){
            return null;
        }else {
            return this.rounds.get(rounds.size()-1);
        }
    }

    public Progress getProgress(){
        return new Progress(id, getRound().getState(), getRound().getWordToGuess(), playerScore(), getRound().getAttempts().get(getRound().getAttempts().size()-1),getRound().getHistory());
    }

    public int getWordLength() {
        return wordLength;
    }

    public Long getId() {
        return id;
    }
}
