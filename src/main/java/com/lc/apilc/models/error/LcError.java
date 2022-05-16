package com.lc.apilc.models.error;

import lombok.Getter;

public class LcError {

    @Getter
    private String error;
    @Getter
    private String code;

    public LcError(String mensagemErro, String errorCode) {
        this.error = mensagemErro;
        this.code = errorCode;
    }

}
