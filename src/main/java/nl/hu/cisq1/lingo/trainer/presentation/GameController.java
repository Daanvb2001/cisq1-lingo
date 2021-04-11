package nl.hu.cisq1.lingo.trainer.presentation;

import nl.hu.cisq1.lingo.trainer.application.GameService;
import nl.hu.cisq1.lingo.trainer.domain.Progress;
import nl.hu.cisq1.lingo.trainer.domain.exceptions.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lingo")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    public Long startGame(){
        return this.gameService.startNewGame();
    }

    @PostMapping("{id}/startround")
    public Progress startRound(@PathVariable Long id) throws IdNotFoundException {
        return this.gameService.startNewRound(id);
    }

    @PostMapping("{id}/guess")
    public Progress guess(@PathVariable Long id,@RequestBody String attempt) throws IdNotFoundException, NoRoundStartedException, GameHasEndedException, NoGameActiveException, NotPlayingException {
        return this.gameService.guess(id, attempt);
    }

}
