package coursework.controllers;

import coursework.model.Game;
import coursework.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("game", gameService.getAll());
        return "game/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("game", gameService.get(id));
        return "game/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("game") Game game) {
        return "game/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("game") @Valid Game game,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "game/new";

        gameService.save(game);
        return "redirect:/game";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("game", gameService.get(id));
        return "game/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("game") @Valid Game game, BindingResult bindingResult,
                         @PathVariable("id") Long id) {
        if (bindingResult.hasErrors())
            return "game/edit";

        gameService.update(id, game);
        return "redirect:/game";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        gameService.delete(id);
        return "redirect:/game";
    }
}
