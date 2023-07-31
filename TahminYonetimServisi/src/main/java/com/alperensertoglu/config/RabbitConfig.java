package com.alperensertoglu.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
//    Exchange, binding türü olmalı
    // 1 - Exchange
    String directExchangeAuth="direct-exchang-score";
    String queueAuth="queue-score";
    String saveBindingKey="save-binding-key";
    @Bean
    DirectExchange directExchangeAuth(){
        return new DirectExchange(directExchangeAuth);
    }
    @Bean
    Queue queueAuth(){
        return new Queue(queueAuth);
    }
    @Bean
    public Binding bindingSaveDirectExchange(final Queue queueAuth,final DirectExchange directExchangeAuth){
        // Binding oluşturmak için exchange, bindingkey ve queue gerek
        return BindingBuilder.bind(queueAuth).to(directExchangeAuth).with(saveBindingKey);
        // kuyruğu exchange'e bindingkey ile bind et
    }
}
