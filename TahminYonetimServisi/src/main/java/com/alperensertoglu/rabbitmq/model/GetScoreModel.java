package com.alperensertoglu.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetScoreModel implements Serializable {

    /**
     * Skor modülüne userid ve skorunu gönderiyoruz ki hangi kullanıcın skorunun ne olduğunu bilelim
     */
    Long userid;
    Long score;
}
