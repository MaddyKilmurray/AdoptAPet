package com.ironhack.animalservice.controller;

import com.ironhack.animalservice.dao.AnimalDAO;
import com.ironhack.animalservice.dto.AnimalDTO;
import com.ironhack.animalservice.enums.AnimalType;
import com.ironhack.animalservice.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/animal")
public class AnimalController {

    @Autowired
    AnimalRepository repository;

/*    @GetMapping
    public List<AnimalDAO> findByTypeAndAge(@RequestParam int startAge, @RequestParam int endAge){
        return repository.getByAgeBetween(startAge, endAge);
    }*/

    @GetMapping("/{id}")
    public AnimalDAO findById(@PathVariable(name = "id") Long id){
        Optional<AnimalDAO> animal = repository.findById(id);
        if (animal.isPresent()){
            return animal.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no animal with id: " + id);
    }

    @GetMapping("/all")
    public List<AnimalDAO> animalList(){
        return repository.findAll();
    }

    @GetMapping
    public List<AnimalDAO> findByAgeRangeAndType(@RequestParam int startAge, @RequestParam int endAge, @RequestParam AnimalType type){
        return repository.getByAgeBetweenAndType(startAge, endAge, type);
    }

    @PostMapping
    public AnimalDAO create(@RequestBody AnimalDTO animalDTO){
        AnimalDAO animal = new AnimalDAO(animalDTO.getName(), AnimalType.valueOf(animalDTO.getType()), animalDTO.getAge(), animalDTO.isAdoptable());
        return repository.save(animal);
    }

    @PatchMapping("/adoptable")
    public AnimalDAO updateStatus(@RequestParam Long animalId, @RequestParam boolean status){
        Optional<AnimalDAO> animal = repository.findById(animalId);
        if (animal.isPresent()){
            animal.get().setAdoptable(status);
            return repository.save(animal.get());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no animal with id: " + animalId);
    }

}
