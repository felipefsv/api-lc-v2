package com.lc.apilc.exception;

import com.lc.apilc.enums.ErrorCodes;
import lombok.Getter;

public class LcException extends RuntimeException {

    @Getter
    private String errorCode;

    public LcException(String message, ErrorCodes errorCode) {
        super(message);
        this.errorCode = errorCode.getErrorCode();
    }
}
