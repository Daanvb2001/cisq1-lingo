package nl.hu.cisq1.lingo.trainer.application;

import nl.hu.cisq1.lingo.trainer.data.SpringGameRepository;
import nl.hu.cisq1.lingo.trainer.domain.Game;
import nl.hu.cisq1.lingo.trainer.domain.GameState;
import nl.hu.cisq1.lingo.trainer.domain.Progress;
import nl.hu.cisq1.lingo.trainer.domain.exceptions.GameHasEndedException;
import nl.hu.cisq1.lingo.trainer.domain.exceptions.NoRoundStartedException;
import nl.hu.cisq1.lingo.trainer.domain.exceptions.idNotFoundException;
import nl.hu.cisq1.lingo.words.application.WordService;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    private WordService wordService;
    private SpringGameRepository gameRepository;

    public GameService(WordService wordService, SpringGameRepository gameRepository) {
        this.wordService = wordService;
        this.gameRepository = gameRepository;
    }

    public long startNewGame(){
        Game game = new Game();
        this.gameRepository.save(game);
        return game.getId();
    }

    public Progress startNewRound(Long id) throws idNotFoundException {
        Game game = this.gameRepository.findById(id).orElseThrow(() -> new idNotFoundException("Game not found"));

        String wordToGuess = wordService.provideRandomWord(game.getWordLength());
        Progress progress = game.startRound(wordToGuess);

        this.gameRepository.save(game);
        return progress;
    }

    public Progress guess(Long id, String attempt) throws idNotFoundException, NoRoundStartedException, GameHasEndedException{
        Game game = this.gameRepository.findById(id).orElseThrow(() -> new idNotFoundException("Game not found"));
        if (game.getRound()==null){
            throw new NoRoundStartedException("There is no round found");
        }
        if (game.getRound().getState().equals(GameState.ELIMINATED)){
            throw new GameHasEndedException("This game has ended");
        }

        Progress progress = game.guess(attempt);
        this.gameRepository.save(game);
        return progress;
    }
}
