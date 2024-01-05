package com.example.auditionapp.service;
import com.example.auditionapp.model.Attribute;
import com.example.auditionapp.model.Auditionee;
import com.example.auditionapp.repository.AttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttributeService {

    private final AttributeRepository attributeRepository;

    @Autowired
    public AttributeService(AttributeRepository attributeRepository) {
        this.attributeRepository = attributeRepository;
    }

    public List<Attribute> getAllAttributes() {
        return (List<Attribute>) attributeRepository.findAll();
    }

    public Attribute getById (Long id) {
        return  attributeRepository.findById(id).orElse(null);
    }

    public Attribute addAttribute(Attribute attribute) {
        return attributeRepository.save(attribute);
    }
}
