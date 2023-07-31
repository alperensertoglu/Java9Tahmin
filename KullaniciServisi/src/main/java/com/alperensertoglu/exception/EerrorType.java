package com.alperensertoglu.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum EerrorType {
    MUSTERI_BULUNAMADI(1003, "Aradığınız müşteri sistemde kayıtlı değil", NOT_FOUND),
    URUN_EKLEME_HATASI(2001, "Ürün ekleme başarısız oldu", INTERNAL_SERVER_ERROR),
    INVALID_PARAMETER(3001, "Geçersiz parametre girişi yaptınız", BAD_REQUEST),
    REGISTER_USERNAME_EXISTS(4100, "Kullanıcı adı zaten var", BAD_REQUEST),
    DOLOGIN_USERNAMEORPASSWORD_NOTEXISTS(4200, "Kullanıcı adı ya da şifre bulunamadı", BAD_REQUEST),
    INVALID_TOKEN(4300,"Geçersiz token",BAD_REQUEST),
    USER_NOT_FOUND(4404,"Kullanıcı bulunamadı",BAD_REQUEST);

    private int code;
    private String mesaj;
    private HttpStatus status;
}
