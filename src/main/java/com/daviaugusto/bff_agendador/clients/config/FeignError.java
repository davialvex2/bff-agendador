package com.daviaugusto.bff_agendador.clients.config;

import com.daviaugusto.bff_agendador.infrastructure.exceptions.BusinessException;
import com.daviaugusto.bff_agendador.infrastructure.exceptions.ConflictException;
import com.daviaugusto.bff_agendador.infrastructure.exceptions.IllegalArgumentException;
import com.daviaugusto.bff_agendador.infrastructure.exceptions.ResourceNotFoundException;
import com.daviaugusto.bff_agendador.infrastructure.exceptions.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class FeignError implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {

        String mensagemErro = mensagemErro(response);

        switch (response.status()){
            case 409:
                return new ConflictException("Erro " + mensagemErro);
            case 403:
                return new ResourceNotFoundException("Erro " + mensagemErro);
            case 401:
                return new UnauthorizedException("Erro " + mensagemErro);
            case 400:
                return new IllegalArgumentException("Erro " + mensagemErro);
            default:
                return new BusinessException("Erro " + mensagemErro);
        }
    }

    private String mensagemErro(Response response){

        try {
            if(Objects.isNull(response.body())){
                return "";
            }
            String mensagemErro = new String(response.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8);
            return mensagemErro;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
