package com.ironhack.adoptingservice.proxy;

import com.ironhack.adoptingservice.dao.AnimalDAO;
import com.ironhack.adoptingservice.dto.AnimalDTO;
import com.ironhack.adoptingservice.enums.AnimalType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@FeignClient("ANIMAL-SERVICE")
@RequestMapping("/animal")
public interface AnimalServiceProxy {

    @GetMapping
    public List<AnimalDAO> findByTypeAndAgeAndType(@RequestParam int startAge, @RequestParam int endAge, @RequestParam AnimalType type);

    @PutMapping("/adoptable")
    public AnimalDAO updateStatus(@RequestParam Long animalId, @RequestParam boolean status);

    @GetMapping("/{id}")
    public AnimalDAO findById(@PathVariable Long id);
}
