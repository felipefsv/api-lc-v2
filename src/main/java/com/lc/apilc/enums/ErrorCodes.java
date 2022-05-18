package com.lc.apilc.enums;

import lombok.Getter;

public enum ErrorCodes {
    USUARIO_INVALIDO("LCE-0001"),
    ENTIDADE_NAO_ENCONTRADA("LCE-0002"),
    ACL_INVALIDO("LCE-0003"),
    CAMPOS_INVALIDOS("LCE-0004");

    @Getter
    private final String errorCode;

    ErrorCodes(String errorCode) {
        this.errorCode = errorCode;
    }

}
