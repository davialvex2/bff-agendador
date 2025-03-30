package com.daviaugusto.bff_agendador.infrastructure.dtos.in;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDTORequest {

    private String email;
    private String senha;

}
