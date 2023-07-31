package com.alperensertoglu.manager;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "generate-city-manager", url = "http://localhost:9091/sehir", decode404 = true)
public interface ITahminManager {

    @GetMapping("/generate")
    ResponseEntity<String> generateCity();
}
