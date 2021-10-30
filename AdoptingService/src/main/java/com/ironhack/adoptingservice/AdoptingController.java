package com.ironhack.adoptingservice;

import com.ironhack.adoptingservice.dto.AdoptedDTO;
import com.ironhack.adoptingservice.dto.AdopterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdoptingController {

    @Autowired
    AdoptingService adoptingService;

    @GetMapping("/adopt")
    @ResponseStatus(HttpStatus.OK)
    public AdoptedDTO adoptAPet() {

    }
}
