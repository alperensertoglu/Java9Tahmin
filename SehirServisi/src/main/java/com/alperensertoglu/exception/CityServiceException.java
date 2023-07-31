package com.alperensertoglu.exception;

import lombok.Getter;

@Getter
public class CityServiceException extends RuntimeException {
    private final EerrorType type;

    public CityServiceException(EerrorType type) {
        super(type.getMesaj());
        this.type = type;
    }

    public CityServiceException(EerrorType type, String mesaj) {
        super(mesaj);
        this.type = type;
    }

}
