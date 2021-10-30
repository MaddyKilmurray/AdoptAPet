package com.ironhack.adoptingservice.dao;

import com.ironhack.adoptingservice.enums.AnimalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AnimalDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long animalId;

    private String name;

    @Enumerated(EnumType.STRING)
    private AnimalType type;

    private int age;

    private boolean isAdoptable;

    public AnimalDAO(String name, AnimalType type, int age, boolean isAdoptable) {
        setName(name);
        setType(type);
        setAge(age);
        setAdoptable(isAdoptable);
    }
}
