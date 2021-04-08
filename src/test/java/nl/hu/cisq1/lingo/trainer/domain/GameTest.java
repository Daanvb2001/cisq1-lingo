package nl.hu.cisq1.lingo.trainer.domain;

import nl.hu.cisq1.lingo.trainer.domain.exceptions.AlreadyPlayingException;
import nl.hu.cisq1.lingo.trainer.domain.exceptions.NoGameActiveException;
import nl.hu.cisq1.lingo.trainer.domain.exceptions.WrongWordLengthException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    Game game;

    @BeforeEach
    void generateGame(){
        game = new Game();
    }
    @Test
    @DisplayName("WordToGuess has too many characters")
    void wordLengthInvalid(){
        assertThrows(WrongWordLengthException.class,()->game.startRound("blaren"));
    }


    @Test
    @DisplayName("there is a round still playing")
    void RoundStillPlaying(){
        game.startRound("baard");
        assertThrows(AlreadyPlayingException.class,()->game.startRound("blaren"));
    }

    @Test
    @DisplayName("round has correct amount of characters")
    void nextRound(){
        game.startRound("baard");
        game.guess("baard");
        assertDoesNotThrow(()->game.startRound("braken"));
    }

    @Test
    @DisplayName("new wordToGuess has to many characters")
    void nextRoundToManyCharacters(){
        game.startRound("baard");
        game.guess("baard");
        assertThrows(WrongWordLengthException.class,()->game.startRound("vechten"));
    }

    @Test
    @DisplayName("new wordToGuess has not enough characters")
    void nextRoundNotEnoughCharacters(){
        game.startRound("baard");
        game.guess("baard");
        assertThrows(WrongWordLengthException.class,()->game.startRound("baard"));
    }

    @Test
    @DisplayName("round has more characters after round win")
    void nextRound7Characters(){
        game.startRound("baard");
        game.guess("baard");
        game.startRound("braken");
        game.guess("braken");
        assertDoesNotThrow(()->game.startRound("vechten"));
    }

    @Test
    @DisplayName("round stays at 6 characters after win")
    void nextRound6InsteadOf7Characters(){
        game.startRound("baard");
        game.guess("baard");
        game.startRound("braken");
        game.guess("breken");
        game.guess("braken");
        assertThrows(WrongWordLengthException.class,()->game.startRound("staart"));
    }

    @Test
    @DisplayName("round does not go to 8 characters")
    void nextRound5Characters(){
        game.startRound("baard");
        game.guess("baard");
        game.startRound("braken");
        game.guess("braken");
        game.startRound("vechten");
        game.guess("vechten");
        assertThrows(WrongWordLengthException.class,()->game.startRound("afgebeld"));
    }

    @Test
    @DisplayName("round goes back to 5 characters")
    void nextRoundnot8Characters(){
        game.startRound("baard");
        game.guess("baard");
        game.startRound("braken");
        game.guess("braken");
        game.startRound("vechten");
        game.guess("vechten");
        assertDoesNotThrow(()->game.startRound("breek"));
    }

    @Test
    @DisplayName("No Game active")
    void NoGameActive(){
        assertThrows(NoGameActiveException.class,()->game.guess("baard"));
    }

    @Test
    @DisplayName("player gets 25 points after win")
    void get25Points(){
        game.startRound("baard");
        game.guess("baard");
        assertEquals(25,game.playerScore());
    }

    @Test
    @DisplayName("player gets 50 points after 2 wins")
    void get50Points(){
        game.startRound("baard");
        game.guess("baard");
        game.startRound("braken");
        game.guess("braken");
        assertEquals(50,game.playerScore());
    }

    @Test
    @DisplayName("player gets 30 points after 1 win with 1 attempt and 1 win with 5 attempts")
    void get30Points(){
        game.startRound("baard");
        game.guess("breek");
        game.guess("braak");
        game.guess("kraak");
        game.guess("draad");
        game.guess("baard");
        game.startRound("braken");
        game.guess("braken");
        assertEquals(30,game.playerScore());
    }

    @Test
    @DisplayName("player gets 25 points after 1 win and the eliminated")
    void get25PointsAfterEliminated(){
        game.startRound("baard");
        game.guess("baard");
        game.startRound("braken");
        game.guess("kraken");
        game.guess("staken");
        game.guess("draken");
        game.guess("breder");
        game.guess("breken");
        assertEquals(25,game.playerScore());
    }

    @Test
    @DisplayName("player gets no points after eliminated")
    void get0Points(){
        game.startRound("baard");
        game.guess("breek");
        game.guess("braak");
        game.guess("kraak");
        game.guess("draad");
        game.guess("steek");
        assertEquals(0,game.playerScore());
    }
}