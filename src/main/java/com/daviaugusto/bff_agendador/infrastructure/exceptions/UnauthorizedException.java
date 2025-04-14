package com.daviaugusto.bff_agendador.infrastructure.exceptions;


public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(String msg){
        super(msg);
    }

    public UnauthorizedException(String msg, Throwable throwable){
        super(msg);
    }
}
