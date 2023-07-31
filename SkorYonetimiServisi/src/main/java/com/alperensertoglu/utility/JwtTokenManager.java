package com.alperensertoglu.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.Optional;

@Component
public class JwtTokenManager {
    @Value("${userservices.secrets.secret-key}")
    String secretKey;
    Long expireDate = 1000L * 60 * 15; // 1 saniye 1000 milisaniye. Çünkü currentTimeMillis içine yazıcaz. Sonra durumda 15 dakika
    @Value("${userservices.secrets.issuer}")
    String issuer = "Alperen";
    // 1. Token üret

    /**
     * Dikkat dikkat
     * Claim objelerinin içine herkes tarafından görülmesini istemediğiniz bilgileri koymamamalısınız.
     * Email, password gibi bilgiler payload içinde olmamalıdır
     * @param id
     * @return
     */
    public Optional<String> createToken(Long id) {
        String token = "";
        try {
            token = JWT.create().withAudience()
                    .withClaim("id", id)
                    .withClaim("whichpage", "AuthMicroService")
                    .withClaim("lastjoin", System.currentTimeMillis())
                    .withClaim("grup", "java9")
                    .withIssuer(issuer) // jwt token oluşturan yapı
                    .withIssuedAt(new Date()) // jwt token oluşturulma zamanı
                    .withExpiresAt(new Date(System.currentTimeMillis() + expireDate))
                    .sign(Algorithm.HMAC512(secretKey)); // Algoritma seçeceğimiz alan. Şifreleme sistemi nasıl olacak. Sayı ne kadar yüksekse o kadar iyi
            return Optional.of(token);
        } catch (Exception ex) {
            return Optional.empty();
        }


    }

    // 2. Tokeni doğrula
    public Boolean verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(secretKey); // Algoritma ve secret key olması lazım
            JWTVerifier jwtVerifier = JWT.require(algorithm).withIssuer(issuer).build();
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            if (decodedJWT == null)
                return false;
        }catch (Exception e){
            return false;
        }
            return true;

    }

    // 3. Tokenden bilgi çıkarımı yapma
    public Optional<Long> getIdFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(secretKey); // Algoritma ve secret key olması lazım
            JWTVerifier jwtVerifier = JWT.require(algorithm).withIssuer(issuer).build();
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            if (decodedJWT == null)
               return Optional.empty();
           Long id = decodedJWT.getClaim("id").asLong();
           String whichpage = decodedJWT.getClaim("whichpage").asString();
            System.out.println("Token whichpage:"+whichpage);
           return Optional.of(id);
        }catch (Exception e){
            return Optional.empty();
        }

    }
}
