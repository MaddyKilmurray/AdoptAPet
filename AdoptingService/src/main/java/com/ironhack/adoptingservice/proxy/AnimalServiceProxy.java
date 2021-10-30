package com.ironhack.adoptingservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("ANIMAL-SERVICE")
public interface AnimalServiceProxy {

}
