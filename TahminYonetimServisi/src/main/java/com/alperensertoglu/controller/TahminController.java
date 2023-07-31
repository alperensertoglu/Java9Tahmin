package com.alperensertoglu.controller;

import static com.alperensertoglu.constant.EndPoints.*;

import com.alperensertoglu.service.TahminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(TAHMIN)
@RequiredArgsConstructor
public class TahminController {
    private final TahminService service;

    @PostMapping(YAP)
    public ResponseEntity<Boolean> tahminEt(@RequestBody String token, @RequestBody String tahmin){
      return ResponseEntity.ok(service.guessPicture(token,tahmin));
    }
}
