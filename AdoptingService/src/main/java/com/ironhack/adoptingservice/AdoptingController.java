package com.ironhack.adoptingservice;

import com.ironhack.adoptingservice.dto.AdoptedDTO;
import com.ironhack.adoptingservice.dto.AdopterDTO;
import com.ironhack.adoptingservice.dto.AdopterRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdoptingController {

    @Autowired
    AdoptingService adoptingService;

    @GetMapping("/adopt/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AdoptedDTO adoptAPet(@PathVariable(name = "id") Long id, @RequestBody AdopterRequestDTO adopterRequestDTO) {
        return adoptingService.adoptAPet(id, adopterRequestDTO);
    }
}
