package com.example.auditionapp.service;
import com.example.auditionapp.model.Auditionee;
import com.example.auditionapp.repository.AuditioneeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AuditioneeService {

    private final AuditioneeRepository auditioneeRepository;

    @Autowired
    public AuditioneeService(AuditioneeRepository auditioneeRepository) {
        this.auditioneeRepository = auditioneeRepository;
    }

    public List<Auditionee> getAllAuditionees() {
        return (List<Auditionee>) auditioneeRepository.findAll();
    }

    public Auditionee addAuditionee(Auditionee auditionee) {
        return auditioneeRepository.save(auditionee);
    }

    // You can add more methods here for other CRUD operations
}

