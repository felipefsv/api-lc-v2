package com.lc.apilc.enums;

import lombok.Getter;

public enum ErrorCodes {
    USUARIO_INVALIDO("LCE_0001"),
    ENTIDADE_NAO_ENCONTRADA("LCE_0002"),
    ACL_INVALIDO("LCE_0003");

    @Getter
    private String errorCode;

    ErrorCodes(String errorCode) {
        this.errorCode = errorCode;
    }

}
