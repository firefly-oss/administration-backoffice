package com.vaadin.starter.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * The entry point of the Spring Boot application.
 */
@SpringBootApplication
@ComponentScan(basePackages = {
    "com.vaadin.starter.business",
    "com.catalis.administration.backoffice.interfaces",
    "com.catalis.administration.backoffice.core"
})
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
