package nl.hu.cisq1.lingo.trainer.application;

import nl.hu.cisq1.lingo.trainer.data.SpringGameRepository;
import nl.hu.cisq1.lingo.trainer.domain.Game;
import nl.hu.cisq1.lingo.trainer.domain.exceptions.*;
import nl.hu.cisq1.lingo.words.application.WordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.Optional;

class GameServiceTest {
    WordService wordService;
    SpringGameRepository gameRepository;
    GameService service;

    @BeforeEach
    void beforeEach(){
        Game game = new Game();
        wordService = mock(WordService.class);
        when(wordService.provideRandomWord(5)).thenReturn("appel");
        when(wordService.provideRandomWord(6)).thenReturn("breken");
        when(wordService.provideRandomWord(7)).thenReturn("vechten");

        gameRepository = mock(SpringGameRepository.class);
        when(gameRepository.findById(0L)).thenReturn(Optional.of(game));

        service = new GameService(wordService, gameRepository);
    }

    @Test
    @DisplayName("Throws exception when no game found")
    void noGameFound(){
        assertThrows(IdNotFoundException.class, () -> service.startNewRound(2L));
        assertThrows(IdNotFoundException.class, () -> service.guess(2L, ""));
    }

    @Test
    @DisplayName("Throws exception when a game is already playing")
    void RoundAlreadyStarted() throws IdNotFoundException {
        service.startNewRound(0L);
        assertThrows(AlreadyPlayingException.class, () -> service.startNewRound(0L));
    }

    @Test
    @DisplayName("Throws exception when a round has not started")
    void GuessingWithoutRoundStarted(){
        assertThrows(NoRoundStartedException.class, () -> service.guess(0L, ""));
    }

    @Test
    @DisplayName("Throws exception when a game has already ended")
    void GuessingWhileGameWasEnded() throws IdNotFoundException, NoGameActiveException, NotPlayingException, NoRoundStartedException {
        service.startNewRound(0L);
        for (int i = 0; i <5; i++)
        service.guess(0L, "brood");
        assertThrows(GameHasEndedException.class, () -> service.guess(0L, "brood"));
    }
}