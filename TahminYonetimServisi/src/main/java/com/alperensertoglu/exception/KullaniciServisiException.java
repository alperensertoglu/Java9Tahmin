package com.alperensertoglu.exception;

import lombok.Getter;

@Getter
public class KullaniciServisiException extends RuntimeException {
    private final EerrorType type;

    public KullaniciServisiException(EerrorType type) {
        super(type.getMesaj());
        this.type = type;
    }

    public KullaniciServisiException(EerrorType type, String mesaj) {
        super(mesaj);
        this.type = type;
    }

}
