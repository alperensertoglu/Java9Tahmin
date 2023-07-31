package com.alperensertoglu.service;

import com.alperensertoglu.dto.request.DeleteCityRequestDto;
import com.alperensertoglu.dto.request.SaveCityRequestDto;
import com.alperensertoglu.dto.request.UpdateCityRequestDto;
import com.alperensertoglu.exception.CityServiceException;
import com.alperensertoglu.exception.EerrorType;
import com.alperensertoglu.mapper.ICityMapper;
import com.alperensertoglu.repository.ICityRepository;
import com.alperensertoglu.repository.entity.City;
import com.alperensertoglu.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CityService extends ServiceManager<City, Long> {
    private final ICityRepository repository;

    /**
     * Constructor Injection
     * @param repository
     */

    public CityService(ICityRepository repository) {
        super(repository);
        this.repository = repository;
    }

    /**
     * Şehir ekleme işlemi. Önce şehir var mı diye kontrol ediyoruz. Yoksa ekleme işlemi gerçekleşiyor.
     * @param dto
     * @return
     */
    public City addCity(SaveCityRequestDto dto) {
        City city = ICityMapper.INSTANCE.toCityFromDto(dto);
        if (repository.existsByName(dto.getName()))
            throw new CityServiceException(EerrorType.CITY_EXISTS);
        return save(city);
    }

    /**
     * Şehir silme işlemi: Önce id ya da ismi ile o şehir var mı diye kontrol edip,
     * Eğer varsa silme işlemini gerçekleştirip yoksa hata fırlatıyoruz
     * @param dto
     */
    public void deleteCity(DeleteCityRequestDto dto) {
        if (repository.existsByNameOrId(dto.getName(), dto.getId())) {
            repository.deleteByIdOrName(dto.getId(), dto.getName());
        } else
            throw new CityServiceException(EerrorType.CITY_DOESNT_EXISTS);
    }

    /**
     * Güncelleme işlemi: Önce isminden şehrin olup olmadığını kontrol ediyoruz. Yoksa hata fırlatıp yoksa bölgesini
     * değiştiriyoruz
     * @param dto
     * @param region
     * @return
     */
    public City update(UpdateCityRequestDto dto, String region) {
        City city = ICityMapper.INSTANCE.toCityUpdate(dto);
        if (!repository.existsByName(dto.getName()))
            throw new CityServiceException(EerrorType.CITY_DOESNT_EXISTS);
        city.setRegion(region);
        return city;

    }


    /**
     * Rastgele Şehir Getirme: ID'sine göre rastgele bir şehir getiriyoruz(Sistemi yormaması için 100 yaptım ama arttırılabilir.)
     * Her rastgele sayı için o ID'de şehir var mı diye kontrol ediyoruz. Bulana kadar Do/While döngüsü devam ediyor.
     * Eğer varsa bu şehrin ismini dönüyoruz ki oyunda kullanıcı doğru ismi girmiş mi kontrol edebiliyoruz
     * @return
     */
    public String generateCity() {
        Random random = new Random();
        Long rand = random.nextLong(0,100);
        boolean check = false;
        do {
            if (!repository.findById(rand).isEmpty())
                check = true;
                return repository.findById(rand).get().getName();
        }while(check == false);

    }
}
