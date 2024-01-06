package com.example.auditionapp.controller;

import com.example.auditionapp.model.Attribute;
import com.example.auditionapp.model.Auditionee;
import com.example.auditionapp.model.Image;
import com.example.auditionapp.model.NoteEntry;
import com.example.auditionapp.service.AttributeService;
import com.example.auditionapp.service.AuditioneeService;
import com.example.auditionapp.service.NoteEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import org.springframework.web.multipart.MultipartFile;
import org.json.JSONObject;


import java.util.Base64;

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
                                    @RequestParam(value = "auditioneeImage", required = false) MultipartFile auditioneeImage) {

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

        // Handle Image Upload
        if (!auditioneeImage.isEmpty()) {
            String imageUrl = uploadImageAndGetUrl(auditioneeImage);
            if (imageUrl != null) {
                Image image = new Image(imageUrl);
                auditionee.setImage(image);
                // Update auditionee with the image URL
                auditioneeService.addAuditionee(auditionee);
            }
        }

        //create note entry
        NoteEntry noteEntry = new NoteEntry();
        noteEntry.setText(noteText);
        noteEntry.setAuditionee(auditionee);

        // Save the NoteEntry
        noteEntryService.addNote(noteEntry);


        return "redirect:/";
    }

//    private String uploadImageAndGetUrl(MultipartFile imageFile) {
//        try {
//            // Convert image to Base64
//            byte[] imageBytes = imageFile.getBytes();
//            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
//
//            // Prepare URL and Request
//            URL url = new URL("https://freeimage.host/api/1/upload");
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("POST");
//            connection.setDoOutput(true);
//            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//
//
//            // Write Parameters to Request
//            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
//            writer.write("key=6d207e02198a847aa98d0a2a901485a5&source=" + base64Image + "&format=json");
//            writer.flush();
//            writer.close();
//
//            // Read Response
//            InputStream responseStream = new BufferedInputStream(connection.getInputStream());
//            String response = new BufferedReader(new InputStreamReader(responseStream))
//                    .lines().collect(Collectors.joining("\n"));
//            responseStream.close();
//
//            // Parse Response
//            JSONObject jsonResponse = new JSONObject(response);
//            return jsonResponse.getJSONObject("image").getString("url");
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

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
