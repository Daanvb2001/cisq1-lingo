package nl.hu.cisq1.lingo.trainer.application;

import nl.hu.cisq1.lingo.CiTestConfiguration;
import nl.hu.cisq1.lingo.trainer.data.SpringGameRepository;
import nl.hu.cisq1.lingo.trainer.domain.GameState;
import nl.hu.cisq1.lingo.trainer.domain.Progress;
import nl.hu.cisq1.lingo.trainer.domain.exceptions.GameHasEndedException;
import nl.hu.cisq1.lingo.trainer.domain.exceptions.IdNotFoundException;
import nl.hu.cisq1.lingo.trainer.domain.exceptions.NoRoundStartedException;
import nl.hu.cisq1.lingo.trainer.domain.exceptions.NotPlayingException;
import nl.hu.cisq1.lingo.words.data.SpringWordRepository;
import nl.hu.cisq1.lingo.words.domain.Word;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@Import(CiTestConfiguration.class)
@Transactional
public class GameServiceIntegrationTest {
    @Autowired
    private GameService gameService;

//    @MockBean
//    private SpringGameRepository gameRepository;

    @MockBean
    private SpringWordRepository wordRepository;

    private Long id;

    @BeforeEach
    void start(){
        when(wordRepository.findRandomWordByLength(5)).thenReturn(Optional.of(new Word("appel")));
        id=gameService.startNewGame();
    }

    @Test
    @DisplayName("test newRound happy path")
    void newRound(){
        Progress startRound = gameService.startNewRound(id);

        assertEquals(startRound.getGameState(), GameState.PLAYING);
        assertEquals(startRound.getScore(), 0);
        assertEquals(startRound.getWordToGuess().length(), 5);
        assertEquals(startRound.getHint(), "a....");
    }

    @Test
    @DisplayName("test win")
    void guessWin(){
        Progress startRound = gameService.startNewRound(id);
        Progress guess = gameService.guess(id, "appel");

        assertEquals(guess.getGameState(), GameState.WIN);
        assertEquals(guess.getScore(), 25);
    }

    @Test
    @DisplayName("test no round")
    void guessNoRound(){
        assertThrows(NoRoundStartedException.class, () -> gameService.guess(id, "appel"));
    }

    @Test
    @DisplayName("test loss")
    void guessloss(){
        Progress startRound = gameService.startNewRound(id);
        gameService.guess(id,"breek");
        gameService.guess(id,"braak");
        gameService.guess(id,"kraak");
        gameService.guess(id,"draad");
        gameService.guess(id,"steek");
        assertThrows(GameHasEndedException.class, () -> gameService.guess(id, "appel"));
    }
}
