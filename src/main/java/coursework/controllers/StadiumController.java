package coursework.controllers;

import coursework.model.Stadium;
import coursework.service.StadiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/stadium")
public class StadiumController {

    private final StadiumService stadiumService;

    @Autowired
    public StadiumController(StadiumService stadiumService) {
        this.stadiumService = stadiumService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("stadium", stadiumService.getAll());
        return "stadium/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("stadium", stadiumService.get(id));
        return "stadium/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("stadium") Stadium stadium) {
        return "stadium/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("stadium") @Valid Stadium stadium,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "stadium/new";

        stadiumService.save(stadium);
        return "redirect:/stadium";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("stadium", stadiumService.get(id));
        return "stadium/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("stadium") @Valid Stadium stadium, BindingResult bindingResult,
                         @PathVariable("id") Long id) {
        if (bindingResult.hasErrors())
            return "stadium/edit";

        stadiumService.update(id, stadium);
        return "redirect:/stadium";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        stadiumService.delete(id);
        return "redirect:/stadium";
    }
}
