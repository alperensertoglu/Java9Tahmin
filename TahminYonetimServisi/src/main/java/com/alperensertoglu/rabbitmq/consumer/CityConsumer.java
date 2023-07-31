package com.alperensertoglu.rabbitmq.consumer;

import com.alperensertoglu.service.TahminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityConsumer {
    private final TahminService tahminService;


}
