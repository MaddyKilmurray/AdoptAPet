package com.ironhack.animalservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AnimalServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnimalServiceApplication.class, args);
    }

}

// 1. application properties x
// 2. DAO Animal and DTO Animal x
// 3. Controller Get, Post, Patch x
// 4. Is service necessary? no
// 5. Animal repo x
