package com.alperensertoglu.exception;

import lombok.Getter;

@Getter
public class UserServiceException extends RuntimeException {
    private final EerrorType type;

    public UserServiceException(EerrorType type) {
        super(type.getMesaj());
        this.type = type;
    }

    public UserServiceException(EerrorType type, String mesaj) {
        super(mesaj);
        this.type = type;
    }

}
