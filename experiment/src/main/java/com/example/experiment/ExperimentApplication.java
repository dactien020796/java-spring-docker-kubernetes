package com.example.experiment;

import com.example.experiment.model.Student;
import com.example.experiment.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
@AllArgsConstructor
public class ExperimentApplication {

	private final StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(ExperimentApplication.class, args);
	}

	@PostConstruct
	private void postConstruct() {
		for (int i = 0; i < 10; i++) {
			studentRepository.save(Student.builder()
				.firstName("Hello")
				.lastName("World " + i)
				.email("hello_word" + i + "@gmail.com")
				.age(20 + i)
				.build());
		}
	}

}
