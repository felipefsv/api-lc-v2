package com.lc.apilc.exception;

public class WorkOrderNotFoundExcepion extends RuntimeException {

    public WorkOrderNotFoundExcepion() {
        super("OS não encontrada");
    }
}
