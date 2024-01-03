package com.example.auditionapp.controller;

import com.example.auditionapp.model.Attribute;
import com.example.auditionapp.model.Auditionee;
import com.example.auditionapp.service.AttributeService;
import com.example.auditionapp.service.AuditioneeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class AuditioneeController {

    private final AuditioneeService auditioneeService;
    private final AttributeService attributeService;

    @Autowired
    public AuditioneeController(AuditioneeService auditioneeService, AttributeService attributeService) {
        this.auditioneeService = auditioneeService;
        this.attributeService = attributeService;
    }

    @GetMapping("/")
    public String displayHome(Model model) {
        Iterable<Auditionee> auditionees = auditioneeService.getAllAuditionees();
        model.addAttribute("auditionees", auditionees);
        return "home";
    }

    @GetMapping("/create")
    public String displayCreateAuditioneeForm (Model model) {
        List<Attribute> attributes = attributeService.getAllAttributes();
        model.addAttribute("attributes", attributes);
        model.addAttribute("auditionee", new Auditionee());
        return "create";
    }

    @PostMapping("/create")
    public String submitCreateForm (@ModelAttribute("auditionee") Auditionee auditionee, @RequestParam List<Long> strengths, @RequestParam List<Long> weaknesses) {
        List<Attribute> strengthAttributes = strengths.stream()
                .map(attributeService::getById)
                .collect(Collectors.toList());
        auditionee.setStrengths(strengthAttributes);

        List<Attribute> weaknessAttributes = weaknesses.stream().
                map(attributeService::getById)
                .collect(Collectors.toList());
        auditionee.setWeaknesses(weaknessAttributes);

        auditioneeService.addAuditionee(auditionee);
        return "redirect:/";
    }

    @GetMapping("/individual/{id}")
    public String displayIndividual (@PathVariable("id") Long id, Model model) {
        model.addAttribute("auditionee", auditioneeService.getById(id));
        return "individual";
    }

}
