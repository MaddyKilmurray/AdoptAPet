package com.ironhack.adopterservice.service.interfaces;

import com.ironhack.adopterservice.dao.Adopter;
import com.ironhack.adopterservice.dto.AdopterReceiptDTO;
import com.ironhack.adopterservice.dto.AdopterRequestDTO;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface IAdopterService {
    AdopterReceiptDTO create(@RequestBody @Valid AdopterRequestDTO adopterRequestDTO);
    List<Adopter> findAllAdopters();
    void populate();
}
