package com.example.auditionapp.controller;

import com.example.auditionapp.model.Auditionee;
import com.example.auditionapp.service.AuditioneeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuditioneeController {

    private final AuditioneeService auditioneeService;

    @Autowired
    public AuditioneeController(AuditioneeService auditioneeService) {
        this.auditioneeService = auditioneeService;
    }

    @GetMapping("/")
    public String displayHome(Model model) {
        Iterable<Auditionee> auditionees = auditioneeService.getAllAuditionees();
        model.addAttribute("auditionees", auditionees);
        return "home";
    }

    @GetMapping("/create")
    public String displayCreateAuditioneeForm () {
        return "create";
    }

}
