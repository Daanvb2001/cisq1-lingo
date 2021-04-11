package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exceptions.InvalidException;
import nl.hu.cisq1.lingo.trainer.domain.exceptions.NotPlayingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoundTest {
    @Test
    @DisplayName("check if first hint is right")
    void firsthintCorrect(){
        Round round =new Round("baard");
        assertEquals("b....", round.firsthint());
    }

    @Test
    @DisplayName("check firstguess")
    void firstGuess() throws NotPlayingException {
        Round round =new Round("baard");
        assertEquals("b...d", round.guess("breed"));
    }

    @Test
    @DisplayName("check invalidGuess")
    void invalidGuess(){
        Round round =new Round("baard");
        assertThrows(InvalidException.class, () -> round.guess("breken"));
    }

    @Test
    @DisplayName("check to many guessess.")
    void StateEliminated() throws NotPlayingException {
        Round round =new Round("baard");
        round.guess("breek");
        round.guess("braak");
        round.guess("kraak");
        round.guess("draad");
        round.guess("steek");
        assertEquals(GameState.ELIMINATED, round.getState());
    }

    @Test
    @DisplayName("Game win")
    void stateWin() throws NotPlayingException {
        Round round =new Round("baard");
        round.guess("breek");
        round.guess("braak");
        round.guess("kraak");
        round.guess("draad");
        round.guess("baard");
        assertEquals(GameState.WIN, round.getState());
    }

    @Test
    @DisplayName("game not found")
    void stateNotPlaying() throws NotPlayingException {
        Round round =new Round("baard");
        round.guess("breek");
        round.guess("braak");
        round.guess("kraak");
        round.guess("draad");
        round.guess("steek");
        assertThrows(NotPlayingException.class, () -> round.guess("strak"));
    }





}