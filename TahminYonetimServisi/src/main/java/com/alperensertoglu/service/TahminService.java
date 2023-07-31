package com.alperensertoglu.service;

import com.alperensertoglu.manager.ITahminManager;
import com.alperensertoglu.rabbitmq.model.GetScoreModel;
import com.alperensertoglu.rabbitmq.producer.ScoreProducer;
import com.alperensertoglu.repository.ITahminRepository;
import com.alperensertoglu.repository.entity.Tahmin;
import com.alperensertoglu.utility.JwtTokenManager;
import com.alperensertoglu.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class TahminService extends ServiceManager<Tahmin, Long> {
    private final ITahminRepository repository;
    private final ITahminManager tahminManager;
    private final ScoreProducer scoreProducer;
    private final JwtTokenManager jwtTokenManager;
    private Tahmin currentUser;

    public TahminService(ITahminRepository repository, ITahminManager tahminManager, ScoreProducer scoreProducer, JwtTokenManager jwtTokenManager) {
        super(repository);
        this.repository = repository;
        this.tahminManager = tahminManager;
        this.scoreProducer = scoreProducer;
        this.jwtTokenManager = jwtTokenManager;
    }

    /**
     * Kullanıcının burda tahmin yapabilmesi için önce Kullanıcı Servisinden giriş yapmış olması gerek. Oradan aldığı tokeni buraya
     * verip burda token'dan id'ye göre hangi kullanıcı olduğunu çözüyoruz. Böylece tahmin yapan oyuncunun hangi oyuncu olduğunu anlamış oluyoruz.
     * feignclient ile şehir'in generatecity diye rastgele şehir üreten url'sine get istek atıyoruz. girdiğimiz tahmin ile şehir aynı ise
     * 10 puan değil ise -10 puan
     * @param token
     * @param tahmin
     * @return
     */
    public Boolean guessPicture(String token, String tahmin) {
        Boolean check = false;
        Long userId = jwtTokenManager.getIdFromToken(token).get();
        currentUser.setUserid(userId);
        if (tahminManager.generateCity().equals(tahmin.toLowerCase())) {
            currentUser.setScore(currentUser.getScore() + 10);
            check = true;
        } else {
            currentUser.setScore(currentUser.getScore() - 10);
            check = false;
        }
        scoreProducer.convertAndSend(GetScoreModel
                .builder()
                .score(currentUser.getScore())
                .userid(userId)
                .build());
        return check;
    }


}
