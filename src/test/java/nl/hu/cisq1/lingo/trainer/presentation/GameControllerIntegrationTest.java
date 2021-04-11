package nl.hu.cisq1.lingo.trainer.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.hu.cisq1.lingo.trainer.data.SpringGameRepository;
import nl.hu.cisq1.lingo.trainer.domain.Game;
import nl.hu.cisq1.lingo.words.data.SpringWordRepository;
import nl.hu.cisq1.lingo.words.domain.Word;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GameControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SpringWordRepository wordRepository;

    @MockBean
    private SpringGameRepository gameRepository;

    @BeforeEach
    void start(){
        when(wordRepository.findRandomWordByLength(5)).thenReturn(Optional.of(new Word("appel")));
        when(wordRepository.findRandomWordByLength(6)).thenReturn(Optional.of(new Word("appels")));
    }

    @Test
    @DisplayName("test startgame")
    void startGame() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/lingo");
        mockMvc.perform(request).andExpect(status().isOk());
    }

    @Test
    @DisplayName("test startRound")
    void startRound() throws Exception {

        Game game = new Game();
        when(gameRepository.findById(0L)).thenReturn(Optional.of(game));

        RequestBuilder request = MockMvcRequestBuilders.post("/lingo/0/startround");
        mockMvc.perform(request).andExpect(status().isOk())
                .andExpect(jsonPath("$.gameState", is("PLAYING")))
                .andExpect(jsonPath("$.score", is(0)))
                .andExpect(jsonPath("$.hint", is("a....")))
                .andExpect(jsonPath("$.wordToGuess", is("appel")));
    }

    @Test
    @DisplayName("test guess")
    void guess() throws Exception {

        Game game = new Game();
        when(gameRepository.findById(0L)).thenReturn(Optional.of(game));

        RequestBuilder request = MockMvcRequestBuilders.post("/lingo/0/startround");
        mockMvc.perform(request).andExpect(status().isOk());

        String guessTry = "apgel";
        System.out.println(guessTry.length());
        RequestBuilder requestguess = MockMvcRequestBuilders.post("/lingo/0/guess").contentType(MediaType.APPLICATION_JSON).content(guessTry);

        mockMvc.perform(requestguess).andExpect(status().isOk())
                .andExpect(jsonPath("$.gameState", is("PLAYING")))
                .andExpect(jsonPath("$.score", is(0)))
                .andExpect(jsonPath("$.hint", is("ap.el")))
                .andExpect(jsonPath("$.wordToGuess", is("appel")));
    }

    @Test
    @DisplayName("test invalid guess")
    void invalidGuess() throws Exception {

        Game game = new Game();
        when(gameRepository.findById(0L)).thenReturn(Optional.of(game));

        RequestBuilder request = MockMvcRequestBuilders.post("/lingo/0/startround");
        mockMvc.perform(request).andExpect(status().isOk());

        String guessTry = "apgeel";
        RequestBuilder requestguess = MockMvcRequestBuilders.post("/lingo/0/guess").contentType(MediaType.APPLICATION_JSON).content(guessTry);

        mockMvc.perform(requestguess)
                .andExpect(jsonPath("$.errorCode", is("NOT FOUND")));
    }

    @Test
    @DisplayName("test round already started")
    void roundAlreadyStarted() throws Exception {

        Game game = new Game();
        when(gameRepository.findById(0L)).thenReturn(Optional.of(game));

        RequestBuilder request = MockMvcRequestBuilders.post("/lingo/0/startround");
        mockMvc.perform(request).andExpect(status().isOk());

        RequestBuilder requestSecondRound = MockMvcRequestBuilders.post("/lingo/0/startround");

        mockMvc.perform(requestSecondRound)
                .andExpect(jsonPath("$.errorCode", is("CONFLICT")));
    }
}