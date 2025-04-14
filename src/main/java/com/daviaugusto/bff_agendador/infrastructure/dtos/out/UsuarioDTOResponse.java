package com.daviaugusto.bff_agendador.infrastructure.dtos.out;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTOResponse {


    private String nome;
    private String email;
    private String senha;
    private List<TelefoneDTOResponse> telefone;
    private List<EnderecoDTOResponse> endereco;



}
