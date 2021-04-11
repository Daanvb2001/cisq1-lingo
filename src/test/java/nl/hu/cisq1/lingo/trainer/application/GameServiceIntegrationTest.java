package nl.hu.cisq1.lingo.trainer.application;

import nl.hu.cisq1.lingo.CiTestConfiguration;
import nl.hu.cisq1.lingo.trainer.data.SpringGameRepository;
import nl.hu.cisq1.lingo.trainer.domain.GameState;
import nl.hu.cisq1.lingo.trainer.domain.Progress;
import nl.hu.cisq1.lingo.trainer.domain.exceptions.*;
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
class GameServiceIntegrationTest {
    @Autowired
    private GameService gameService;

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
    void newRound() throws IdNotFoundException {
        Progress startRound = gameService.startNewRound(id);

        assertEquals(GameState.PLAYING, startRound.getGameState());
        assertEquals(0, startRound.getScore());
        assertEquals(5, startRound.getWordToGuess().length());
        assertEquals("a....", startRound.getHint());
    }

    @Test
    @DisplayName("test win")
    void guessWin() throws IdNotFoundException, NoGameActiveException, NotPlayingException, NoRoundStartedException {
        Progress startRound = gameService.startNewRound(id);
        Progress guess = gameService.guess(id, "appel");

        assertEquals(GameState.WIN, guess.getGameState());
        assertEquals(25, guess.getScore());
    }

    @Test
    @DisplayName("test no round")
    void guessNoRound(){
        assertThrows(NoRoundStartedException.class, () -> gameService.guess(id, "appel"));
    }

    @Test
    @DisplayName("test loss")
    void guessloss() throws IdNotFoundException, NoGameActiveException, NotPlayingException, NoRoundStartedException {
        Progress startRound = gameService.startNewRound(id);
        gameService.guess(id,"breek");
        gameService.guess(id,"braak");
        gameService.guess(id,"kraak");
        gameService.guess(id,"draad");
        gameService.guess(id,"steek");
        assertThrows(GameHasEndedException.class, () -> gameService.guess(id, "appel"));
    }
}
