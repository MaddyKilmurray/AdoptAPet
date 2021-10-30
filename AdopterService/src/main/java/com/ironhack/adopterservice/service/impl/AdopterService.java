package com.ironhack.adopterservice.service.impl;

import com.ironhack.adopterservice.dao.Adopter;
import com.ironhack.adopterservice.dto.AdopterReceiptDTO;
import com.ironhack.adopterservice.dto.AdopterRequestDTO;
import com.ironhack.adopterservice.repository.AdopterRepository;
import com.ironhack.adopterservice.service.interfaces.IAdopterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdopterService implements IAdopterService {
    @Autowired
    AdopterRepository adopterRepository;

    public AdopterReceiptDTO create(AdopterRequestDTO adopterRequestDTO) {
        // Validate if Adopter already exists
        Optional<Adopter> optionalAdopter = adopterRepository.findByName(adopterRequestDTO.getName());
        if (optionalAdopter.isPresent()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Adopter with name " + adopterRequestDTO.getName() + " already exist");
        Adopter adopter = new Adopter(adopterRequestDTO.getName(), adopterRequestDTO.getPet());
        adopterRepository.save(adopter);


        AdopterReceiptDTO adopterReceiptDTO= new AdopterReceiptDTO(adopter.getId(), adopter.getName(),adopter.getPet());
        return adopterReceiptDTO;
    }

    public List<Adopter> findAllAdopters() {
        return adopterRepository.findAll();
    }

    public void populate(){
        var allAdopters = new ArrayList<Adopter>();
        allAdopters.add(new Adopter("Tatiana", 3l));
        allAdopters.add(new Adopter("Sofia", 2l));
        adopterRepository.saveAll(allAdopters);
    }

}
