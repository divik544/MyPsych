package com.psych.game.controller;

import com.psych.game.Utils;
import com.psych.game.model.Game;
import com.psych.game.model.GameMode;
import com.psych.game.model.Player;
import com.psych.game.repository.GameRepository;
import com.psych.game.repository.PlayerRepository;
import com.psych.game.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class game {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    GameRepository gameRepository;

    @GetMapping("/create/{pid}/{gm}/{nr}")
    public String createGame(@PathVariable(value = "pid") Long playerId,
                             @PathVariable(value = "gm") int gameMode,
                             @PathVariable(value = "nr") int numRounds) throws Exception {
        Player player = playerRepository.findById(playerId).orElseThrow(()-> new Exception("Player not found"));

        GameMode mode = GameMode.IS_THIS_A_FACT;

        Game game = new Game();

        game.setNumRounds(numRounds);
        game.setLeader(player);
        game.setGameMode(mode);
        game.getPlayers().add(player);

        gameRepository.save(game);

        return ""+game.getId()+"-"+ Utils.getSecretCodeById(game.getId());
    }
}
