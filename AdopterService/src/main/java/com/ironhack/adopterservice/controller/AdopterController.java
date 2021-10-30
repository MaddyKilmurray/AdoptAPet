package com.ironhack.adopterservice.controller;

import com.ironhack.adopterservice.dao.Adopter;
import com.ironhack.adopterservice.dto.AdopterReceiptDTO;
import com.ironhack.adopterservice.dto.AdopterRequestDTO;
import com.ironhack.adopterservice.repository.AdopterRepository;
import com.ironhack.adopterservice.service.interfaces.IAdopterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/adopter")
public class AdopterController {

    @Autowired
    IAdopterService adopterService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AdopterReceiptDTO create(@RequestBody @Valid AdopterRequestDTO adopterRequestDTO) {
        return adopterService.create(adopterRequestDTO);
    }

    @GetMapping
    public List<Adopter> findAllAdopters() {
        return adopterService.findAllAdopters();
    }

    @GetMapping("/populate")
    public void populate(){
        adopterService.populate();
    }
}
