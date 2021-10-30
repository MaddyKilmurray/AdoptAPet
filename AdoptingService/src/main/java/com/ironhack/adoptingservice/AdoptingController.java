package com.ironhack.adoptingservice;

import com.ironhack.adoptingservice.dao.AnimalDAO;
import com.ironhack.adoptingservice.dto.AdoptedDTO;
import com.ironhack.adoptingservice.dto.AdopterDTO;
import com.ironhack.adoptingservice.dto.AdopterReceiptDTO;
import com.ironhack.adoptingservice.dto.AdopterRequestDTO;
import com.ironhack.adoptingservice.enums.AnimalType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdoptingController {

    @Autowired
    AdoptingService adoptingService;

    @PostMapping("/adopt")
    @ResponseStatus(HttpStatus.OK)
    public AdoptedDTO adoptAPet(@RequestBody AdopterRequestDTO adopterRequestDTO) {
        return adoptingService.adoptAPet(adopterRequestDTO);
    }

    @GetMapping("/adopt")
    @ResponseStatus(HttpStatus.OK)
    public List<AnimalDAO> findAnimalByProperty(@RequestParam int startAge, @RequestParam int endAge, @RequestParam AnimalType type) {
        return adoptingService.findAnimalByProperty(startAge,endAge,type);
    }

    @PostMapping("/adopter")
    @ResponseStatus(HttpStatus.CREATED)
    public AdopterReceiptDTO create(@RequestBody AdopterRequestDTO adopterRequestDTO) {
        return adoptingService.createAdopter(adopterRequestDTO);
    }
}
