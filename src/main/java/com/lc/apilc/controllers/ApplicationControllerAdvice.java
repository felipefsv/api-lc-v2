package com.lc.apilc.controllers;

import com.lc.apilc.enums.ErrorCodes;
import com.lc.apilc.exception.WorkOrderNotFoundExcepion;
import com.lc.apilc.models.error.LcError;
import com.lc.apilc.exception.LcException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(LcException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public LcError handleLcException(LcException lcException) {
        String errorMeessage = lcException.getMessage();
        return new LcError(errorMeessage, lcException.getErrorCode());
    }

    @ExceptionHandler(WorkOrderNotFoundExcepion.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public LcError handleWorkOrderNotFoundException(WorkOrderNotFoundExcepion workOrderNotFoundExcepion) {
        String errorMeessage = workOrderNotFoundExcepion.getMessage();
        return new LcError(errorMeessage, ErrorCodes.ENTIDADE_NAO_ENCONTRADA.getErrorCode());
    }

}
