package com.example.experiment.controller;

import com.example.experiment.model.TestCustomAnnotation;
import com.example.experiment.util.ObjectToJsonConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping
    public String annotation() throws IllegalAccessException {
        TestCustomAnnotation object = TestCustomAnnotation.builder()
                .firstName("Tien")
                .lastName("Le")
                .age("26")
                .build();
        return ObjectToJsonConverter.convert(object);
    }
}
