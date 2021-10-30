package com.ironhack.adopterservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdopterReceiptDTO {
    private Long id;

    private String name;

    private Long pet;
}
