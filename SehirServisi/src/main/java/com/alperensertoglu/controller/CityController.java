package com.alperensertoglu.controller;

import static com.alperensertoglu.constant.EndPoints.*;

import com.alperensertoglu.dto.request.DeleteCityRequestDto;
import com.alperensertoglu.dto.request.SaveCityRequestDto;
import com.alperensertoglu.dto.request.UpdateCityRequestDto;
import com.alperensertoglu.repository.entity.City;
import com.alperensertoglu.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(SEHIR)
@RequiredArgsConstructor
public class CityController {
    private final CityService service;

    /**
     * Şehir ekleme işlemi
     * @param dto
     * @return
     */
    @PostMapping(EKLE)
    public ResponseEntity<City> addCity(@RequestBody SaveCityRequestDto dto){
        return ResponseEntity.ok(service.addCity(dto));
    }

    /**
     * ID'sine göre şehir silme işlemi
     * @param dto
     * @return
     */
    @DeleteMapping(DELETE)
    public ResponseEntity<Void> deleteCity(@RequestBody DeleteCityRequestDto dto){
        service.deleteById(dto.getId());
        return ResponseEntity.ok().build();
    }

    /**
     * Bu senaryoda şehrin bölgesini değiştiriyoruz.
     * @param dto
     * @param region
     * @return
     */
    @PostMapping(UPDATE)
    public ResponseEntity<City> updateCity(@RequestBody UpdateCityRequestDto dto, String region){
      return ResponseEntity.ok(service.update(dto,region));
    }

    /**
     * Bize rastgele bir şehir üretiyor. (Var olanlardan)
     * @return
     */
    @GetMapping("/generate")
    public ResponseEntity<String> generateCity(){
      return ResponseEntity.ok(service.generateCity());
    }
}
