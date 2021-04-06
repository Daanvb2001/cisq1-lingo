package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exceptions.AlreadyPlayingException;
import nl.hu.cisq1.lingo.trainer.domain.exceptions.NoGameActive;
import nl.hu.cisq1.lingo.trainer.domain.exceptions.WrongWordLengthException;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private int score;
    private int wordLength;
    private List<Round> rounds;

    public Game() {
        this.score = 0;
        this.rounds=new ArrayList<>();
        this.wordLength=5;
    }

    public void startRound(String wordToGuess){
        if (wordToGuess.length()!=wordLength){
            throw new WrongWordLengthException("Word has the wrong length!");
        }
        if (!rounds.isEmpty()){
            if (this.rounds.get(rounds.size()-1).getState()==GameState.PLAYING){
                throw new AlreadyPlayingException("There is a game still active!");
            }
        }
        Round round = new Round(wordToGuess);
        this.rounds.add(round);
        amountOfCharacters();
    }

    public void guess(String attempt){
        if (rounds.isEmpty()){
            throw new NoGameActive("There is no active game");
        }
        Round round = rounds.get(rounds.size()-1);
        round.guess(attempt);
    }

    public void amountOfCharacters(){
        if (wordLength==5){
            wordLength=6;
        } else if (wordLength==6){
            wordLength=7;
        } else if (wordLength==7){
            wordLength=5;
        }
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
}
