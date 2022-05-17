package com.lc.apilc.models.error;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class LcError {

    @Getter
    private String code;
    @Getter
    private List<String> errors;

    public LcError(String mensagemErro, String errorCode) {
        this.errors = Arrays.asList(mensagemErro);
        this.code = errorCode;
    }

    public LcError(List<String> errors, String errorCode) {
        this.code = errorCode;
        this.errors = errors;
    }

}
