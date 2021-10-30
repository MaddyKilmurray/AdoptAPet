package com.ironhack.adoptingservice;

import com.ironhack.adoptingservice.dao.AnimalDAO;
import com.ironhack.adoptingservice.dto.AdoptedDTO;
import com.ironhack.adoptingservice.dto.AdopterDTO;
import com.ironhack.adoptingservice.dto.AnimalDTO;
import com.ironhack.adoptingservice.enums.AnimalType;
import com.ironhack.adoptingservice.proxy.AdopterServiceProxy;
import com.ironhack.adoptingservice.proxy.AnimalServiceProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public AdoptedDTO adoptAPet(Long id, AdopterDTO adopterDTO) {
        AnimalDTO foundAnimal = findAnimalById(id);
        if (foundAnimal == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"The requested pet could not be found.");
        }
        if (!foundAnimal.isAdoptable()) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"The requested pet has already been adopted.");
        }
        createAdopter(adopterDTO);
        updateStatus(id,false);
        AdoptedDTO adoptedDTO = new AdoptedDTO();
    }

    public AnimalDTO findAnimalById(Long id) {
        CircuitBreaker circuitBreaker = createCircuitBreaker();
        return circuitBreaker.run(() -> animalServiceProxy.getById(id),
                throwable -> getByIdFallback());
    }

    public AnimalDAO updateStatus(Long id, Boolean adoptable) {
        CircuitBreaker circuitBreaker = createCircuitBreaker();
        return circuitBreaker.run(() -> animalServiceProxy.updateStatus(id,adoptable),
                throwable -> patchAnimalFallback());
    }

    public List<AnimalDAO> findAnimalByProperty(int startAge, int endAge, AnimalType type) {
        CircuitBreaker circuitBreaker = createCircuitBreaker();
        return circuitBreaker.run(() -> animalServiceProxy.findByTypeAndAgeAndType(startAge,endAge,type),
                throwable -> getListFallback());
    }

    public void createAdopter(AdopterDTO adopterDTO) {
        CircuitBreaker circuitBreaker = createCircuitBreaker();
        return circuitBreaker.run(() -> adopterServiceProxy.createAdopter(adopterDTO),
                throwable -> createAdopterFallback());
    }

    public CircuitBreaker createCircuitBreaker() {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
        return circuitBreaker;
    }

    public AnimalDTO getByIdFallback() {
        return null;
    }

    public List<AnimalDAO> getListFallback() {
        return null;
    }

    public void createAdopterFallback() {
        return;
    }

    public AnimalDAO patchAnimalFallback() {
        return;
    }
}
