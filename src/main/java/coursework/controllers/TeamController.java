package coursework.controllers;

import coursework.model.Team;
import coursework.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/team")
public class TeamController {

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("team", teamService.getAll());
        return "team/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("team", teamService.get(id));
        return "team/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("team") Team team) {
        return "team/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("team") @Valid Team team,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "team/new";

        teamService.save(team);
        return "redirect:/team";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("team", teamService.get(id));
        return "team/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("team") @Valid Team team, BindingResult bindingResult,
                         @PathVariable("id") Long id) {
        if (bindingResult.hasErrors())
            return "team/edit";

        teamService.update(id, team);
        return "redirect:/team";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        teamService.delete(id);
        return "redirect:/team";
    }
}
