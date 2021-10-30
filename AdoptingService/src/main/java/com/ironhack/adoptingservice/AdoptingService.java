package com.ironhack.adoptingservice;

import com.ironhack.adoptingservice.dao.AnimalDAO;
import com.ironhack.adoptingservice.dto.*;
import com.ironhack.adoptingservice.enums.AnimalType;
import com.ironhack.adoptingservice.proxy.AdopterServiceProxy;
import com.ironhack.adoptingservice.proxy.AnimalServiceProxy;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AdoptingService {

    final AdopterServiceProxy adopterServiceProxy;
    final AnimalServiceProxy animalServiceProxy;

    final CircuitBreakerFactory circuitBreakerFactory;

    public AdoptingService(AdopterServiceProxy adopterServiceProxy, AnimalServiceProxy animalServiceProxy, CircuitBreakerFactory circuitBreakerFactory) {
        this.adopterServiceProxy = adopterServiceProxy;
        this.animalServiceProxy = animalServiceProxy;
        this.circuitBreakerFactory = circuitBreakerFactory;
    }

    public AdoptedDTO adoptAPet(AdopterRequestDTO adopterDTO) {
        AnimalDAO foundAnimal = findAnimalById(adopterDTO.getPet());
        if (!foundAnimal.isAdoptable()) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"The requested pet has already been adopted.");
        }
        createAdopter(adopterDTO);
        updateStatus(foundAnimal.getAnimalId(),false);
        AdoptedDTO adoptedDTO = new AdoptedDTO(foundAnimal.getName(), foundAnimal.getType().toString(), adopterDTO.getName());
        return adoptedDTO;
    }

    public AnimalDAO findAnimalById(Long id) {
        CircuitBreaker circuitBreaker = createCircuitBreaker();
        return circuitBreaker.run(() -> animalServiceProxy.findById(id),
                throwable -> getByIdFallback());
    }

    public AnimalDAO updateStatus(Long id, boolean status) {
//        CircuitBreaker circuitBreaker = createCircuitBreaker();
//        return circuitBreaker.run(() -> animalServiceProxy.updateStatus(id,status),
//                throwable -> patchAnimalFallback());
        return animalServiceProxy.updateStatus(id,status);
    }

    public List<AnimalDAO> findAnimalByProperty(int startAge, int endAge, AnimalType type) {
        CircuitBreaker circuitBreaker = createCircuitBreaker();
        return circuitBreaker.run(() -> animalServiceProxy.findByTypeAndAgeAndType(startAge,endAge,type),
                throwable -> getListFallback());
    }

    public AdopterReceiptDTO createAdopter(AdopterRequestDTO adopterDTO) {
        CircuitBreaker circuitBreaker = createCircuitBreaker();
        return circuitBreaker.run(() -> adopterServiceProxy.create(adopterDTO),
                throwable -> createAdopterFallback());
    }

    public CircuitBreaker createCircuitBreaker() {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
        return circuitBreaker;
    }

    public AnimalDAO getByIdFallback() {
        return null;
    }

    public List<AnimalDAO> getListFallback() {
        return null;
    }

    public AdopterReceiptDTO createAdopterFallback() {
        return null;
    }

    public AnimalDAO patchAnimalFallback() {
        return null;
    }
}
