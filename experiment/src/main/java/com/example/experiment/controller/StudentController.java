package com.example.experiment.controller;

import com.example.experiment.annotation.LogExecutionTime;
import com.example.experiment.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/students")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @LogExecutionTime
    @GetMapping
    public ResponseEntity<?> findAll(
            @RequestParam(defaultValue = "5", required = false) Integer size,
            @RequestParam(defaultValue = "0", required = false) Integer page) {
        Pageable pageable = PageRequest.of(page, size);
        Page<?> result =  studentService.findAll(pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("students", result.getContent());
        response.put("currentPage", result.getNumber());
        response.put("totalItems", result.getTotalElements());
        response.put("totalPages", result.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
