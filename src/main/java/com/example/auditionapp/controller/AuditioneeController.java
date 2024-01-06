package com.example.auditionapp.controller;

import com.example.auditionapp.model.Attribute;
import com.example.auditionapp.model.Auditionee;
import com.example.auditionapp.model.NoteEntry;
import com.example.auditionapp.service.AttributeService;
import com.example.auditionapp.service.AuditioneeService;
import com.example.auditionapp.service.NoteEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class AuditioneeController {

    private final AuditioneeService auditioneeService;
    private final AttributeService attributeService;
    private final NoteEntryService noteEntryService;

    @Autowired
    public AuditioneeController(AuditioneeService auditioneeService, AttributeService attributeService, NoteEntryService noteEntryService) {
        this.auditioneeService = auditioneeService;
        this.attributeService = attributeService;
        this.noteEntryService = noteEntryService;

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
    public String submitCreateForm (@ModelAttribute("auditionee") Auditionee auditionee,
                                    @RequestParam List<Long> strengths,
                                    @RequestParam List<Long> weaknesses,
                                    @RequestParam String noteText,
                                    @RequestParam("auditioneeImage") MultipartFile auditioneeImage) {

        List<Attribute> strengthAttributes = strengths.stream()
                .map(attributeService::getById)
                .collect(Collectors.toList());
        auditionee.setStrengths(strengthAttributes);

        List<Attribute> weaknessAttributes = weaknesses.stream().
                map(attributeService::getById)
                .collect(Collectors.toList());
        auditionee.setWeaknesses(weaknessAttributes);

        //create auditionee first to get id
        auditioneeService.addAuditionee(auditionee);

//        // Handle Image Upload
//        if (!auditioneeImage.isEmpty()) {
//            String imageUrl = uploadImageAndGetUrl(auditioneeImage);
//            if (imageUrl != null) {
//                auditionee.setImageUrl(imageUrl);
//                // Update auditionee with the image URL
//                auditioneeService.updateAuditionee(auditionee);
//            }
//        }

        //create note entry
        NoteEntry noteEntry = new NoteEntry();
        noteEntry.setText(noteText);
        noteEntry.setAuditionee(auditionee);

        // Save the NoteEntry
        noteEntryService.addNote(noteEntry);


        return "redirect:/";
    }

    private String uploadImageAndGetUrl(MultipartFile imageFile) {
        // Logic to send the image to the API and parse the response
        // Return the image URL or null if the upload failed
        return "urlstringhere";
    }

    @GetMapping("/individual/{id}")
    public String displayIndividual (@PathVariable("id") Long id, Model model) {
        model.addAttribute("auditionee", auditioneeService.getById(id));
        return "individual";
    }

    @PostMapping("/individual/{id}/delete")
    public String deleteAuditionee(@PathVariable("id") Long id) {
        auditioneeService.deleteById(id);
        return "redirect:/";
    }

}
