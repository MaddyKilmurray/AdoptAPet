package com.ironhack.adoptingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@AllArgsConstructor
public class AnimalDTO {

    private String name;

    private String type;

    private int age;

    private boolean isAdoptable;
}

