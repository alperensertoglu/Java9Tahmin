package com.alperensertoglu.rabbitmq.producer;

import com.alperensertoglu.rabbitmq.model.GetScoreModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScoreProducer {
    private final RabbitTemplate rabbitTemplate;

    /**
     * Modeli Skor servisine göndermek için
     * @param model
     */
    public void convertAndSend(GetScoreModel model) {
        rabbitTemplate.convertAndSend("direct-exchang-score","save-binding-key",model);
    }
}
