package com.ironhack.adoptingservice.proxy;

import com.ironhack.adoptingservice.dao.Adopter;
import com.ironhack.adoptingservice.dto.AdopterReceiptDTO;
import com.ironhack.adoptingservice.dto.AdopterRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
import java.util.List;

@FeignClient("ADOPTER-SERVICE")
public interface AdopterServiceProxy {

    @PostMapping("/adopter")
    @ResponseStatus(HttpStatus.CREATED)
    public AdopterReceiptDTO create(@RequestBody AdopterRequestDTO adopterRequestDTO);

    @GetMapping("/adopter")
    public List<Adopter> findAllAdopters();

    @GetMapping("/adopter/populate")
    public void populate();
}
