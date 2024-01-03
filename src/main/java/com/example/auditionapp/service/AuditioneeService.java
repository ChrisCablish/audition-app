package com.example.auditionapp.service;
import com.example.auditionapp.model.Auditionee;
import com.example.auditionapp.repository.AuditioneeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AuditioneeService {

    private final AuditioneeRepository auditioneeRepository;

    @Autowired
    public AuditioneeService(AuditioneeRepository auditioneeRepository) {
        this.auditioneeRepository = auditioneeRepository;
    }

    public Auditionee getById(Long id) {
        return auditioneeRepository.findById(id).orElse(null);
    }
    public List<Auditionee> getAllAuditionees() {
        return (List<Auditionee>) auditioneeRepository.findAll();
    }

    public void addAuditionee(Auditionee auditionee) {
        auditioneeRepository.save(auditionee);
    }

    // You can add more methods here for other CRUD operations
}

