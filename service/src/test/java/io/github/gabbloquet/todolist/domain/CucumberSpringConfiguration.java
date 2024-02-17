package io.github.gabbloquet.todolist.domain;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@CucumberContextConfiguration
@SpringBootTest(classes = TodolistSpringTestConfig.class)
public class CucumberSpringConfiguration {
}
