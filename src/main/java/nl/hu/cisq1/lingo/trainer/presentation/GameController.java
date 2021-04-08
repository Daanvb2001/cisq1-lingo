package nl.hu.cisq1.lingo.trainer.presentation;

import nl.hu.cisq1.lingo.trainer.application.GameService;
import nl.hu.cisq1.lingo.trainer.domain.Progress;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Progress startRound(@PathVariable Long id){
        return this.gameService.startNewRound(id);
    }
    @PostMapping("{id}/guess")
    public Progress guess(@PathVariable Long id, String attempt){
        return this.gameService.guess(id, attempt);
    }

}
