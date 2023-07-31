package com.alperensertoglu.rabbitmq.consumer;


import com.alperensertoglu.rabbitmq.model.GetScoreModel;
import com.alperensertoglu.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetScoreConsumer {

    private final UserService userService;


    @RabbitListener(queues = "queue-score")
    public void getScore(GetScoreModel model) {
        userService.saveModel(model);
    }
}
