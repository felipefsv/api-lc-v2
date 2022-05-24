package com.lc.apilc.enums;

import lombok.Getter;

public enum ErrorCodes {
    LOGIN_INVALIDO("LCE-0001"),
    ENTIDADE_NAO_ENCONTRADA("LCE-0002"),
    ACL_INVALIDO("LCE-0003"),
    CAMPOS_INVALIDOS("LCE-0004"),
    OPERACAO_ILEGAL("LCE-0005"),

    TOKEN_INVALIDO("LCE-0006");

    @Getter
    private final String errorCode;

    ErrorCodes(String errorCode) {
        this.errorCode = errorCode;
    }

}
