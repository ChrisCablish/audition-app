package com.example.auditionapp.controller;

import com.example.auditionapp.model.Attribute;
import com.example.auditionapp.model.Auditionee;
import com.example.auditionapp.service.AttributeService;
import com.example.auditionapp.service.AuditioneeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
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
    public String submitCreateForm (@ModelAttribute("auditionee") Auditionee auditionee, @RequestParam List<Long> strengths) {
        List<Attribute> strengthAttributes = strengths.stream()
                .map(attributeService::getById)
                .collect(Collectors.toList());
        auditionee.setStrengths(strengthAttributes);
        auditioneeService.addAuditionee(auditionee);
        return "redirect:/";
    }

}
