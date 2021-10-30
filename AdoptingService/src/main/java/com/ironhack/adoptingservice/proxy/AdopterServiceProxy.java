package com.ironhack.adoptingservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("ADOPTER-SERVICE")
public interface AdopterServiceProxy {


}
