package com.ironhack.adoptingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AdoptedDTO {
    public String petName;
    public String petType;
    public String adopterName;
}
