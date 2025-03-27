package com.daviaugusto.bff_agendador.infrastructure.dtos.in;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTORequest {


    private String nome;
    private String email;
    private String senha;
    private List<TelefoneDTORequest> telefone;
    private List<EnderecoDTORequest> endereco;



}
