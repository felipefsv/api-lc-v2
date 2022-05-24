package com.lc.apilc.controllers;

import com.lc.apilc.enums.ErrorCodes;
import com.lc.apilc.exception.WorkOrderNotFoundExcepion;
import com.lc.apilc.models.error.LcError;
import com.lc.apilc.exception.LcException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(LcException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public LcError handleLcException(LcException lcException) {
        String errorMessage = lcException.getMessage();
        return new LcError(errorMessage, lcException.getErrorCode());
    }

    @ExceptionHandler(WorkOrderNotFoundExcepion.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public LcError handleWorkOrderNotFoundException(WorkOrderNotFoundExcepion workOrderNotFoundExcepion) {
        String errorMessage = workOrderNotFoundExcepion.getMessage();
        return new LcError(errorMessage, ErrorCodes.ENTIDADE_NAO_ENCONTRADA.getErrorCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public LcError handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        List<String> errors = methodArgumentNotValidException
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        return new LcError(errors, ErrorCodes.CAMPOS_INVALIDOS.getErrorCode());
    }

}
