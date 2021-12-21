package coursework.controllers;

import coursework.model.Player;
import coursework.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/player")
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("player", playerService.getAll());
        return "player/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("player", playerService.get(id));
        return "player/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("player") Player player) {
        return "player/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("player") @Valid Player player,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "player/new";

        playerService.save(player);
        return "redirect:/player";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("player", playerService.get(id));
        return "player/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("player") @Valid Player player, BindingResult bindingResult,
                         @PathVariable("id") Long id) {
        if (bindingResult.hasErrors())
            return "player/edit";

        playerService.update(id, player);
        return "redirect:/player";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        playerService.delete(id);
        return "redirect:/player";
    }
}
