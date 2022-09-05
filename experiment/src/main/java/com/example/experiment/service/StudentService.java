package com.example.experiment.service;

import com.example.experiment.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository repository;

    public Page<?> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
