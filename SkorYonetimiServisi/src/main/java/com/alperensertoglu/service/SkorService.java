package com.alperensertoglu.service;

import com.alperensertoglu.rabbitmq.model.GetScoreModel;
import com.alperensertoglu.repository.ISkorRepository;
import com.alperensertoglu.repository.entity.Skor;
import com.alperensertoglu.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SkorService extends ServiceManager<Skor,Long> {
    private final ISkorRepository repository;


    public SkorService(ISkorRepository repository) {
        super(repository);
        this.repository = repository;
    }

    /**
     * Tahmin modülünden gelen mesajın kontrolünü yapıyoruz.
     * @param model
     */
    public void saveModel(GetScoreModel model) {
      Optional<Skor> skor = (repository.findOptionalByUserid(model.getUserid()));
      if (skor.isEmpty()) {
          save(skor.get());
      }else
          update(skor.get());

    }


}
