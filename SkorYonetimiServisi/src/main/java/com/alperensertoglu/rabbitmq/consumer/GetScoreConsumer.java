package com.alperensertoglu.rabbitmq.consumer;

import com.alperensertoglu.mapper.IScoreMapper;
import com.alperensertoglu.rabbitmq.model.GetScoreModel;
import com.alperensertoglu.repository.ISkorRepository;
import com.alperensertoglu.repository.entity.Skor;
import com.alperensertoglu.service.SkorService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetScoreConsumer {

    private final SkorService service;



    @RabbitListener(queues = "queue-score")
    public void getScore(GetScoreModel model){
        Skor skor = IScoreMapper.INSTANCE.toScore(model);
        service.saveModel(model);
    }
}
