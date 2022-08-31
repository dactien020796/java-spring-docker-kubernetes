package com.example.experiment.model;

import com.example.experiment.annotation.JsonElement;
import com.example.experiment.annotation.JsonSerializable;
import lombok.Builder;

@Builder
@JsonSerializable
public class TestCustomAnnotation {

    @JsonElement(key = "first_name")
    private String firstName;

    @JsonElement(key = "last_name")
    private String lastName;

    @JsonElement(key = "student_age")
    private String age;
}
