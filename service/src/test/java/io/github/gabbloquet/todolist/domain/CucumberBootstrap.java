package io.github.gabbloquet.todolist.domain;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = TodolistSpringTestConfig.class)
public class CucumberBootstrap {
}
