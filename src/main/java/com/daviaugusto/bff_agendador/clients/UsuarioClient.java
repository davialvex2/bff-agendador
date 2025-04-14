package com.daviaugusto.bff_agendador.clients;

import com.daviaugusto.bff_agendador.infrastructure.dtos.in.EnderecoDTORequest;
import com.daviaugusto.bff_agendador.infrastructure.dtos.in.LoginDTORequest;
import com.daviaugusto.bff_agendador.infrastructure.dtos.in.TelefoneDTORequest;
import com.daviaugusto.bff_agendador.infrastructure.dtos.in.UsuarioDTORequest;
import com.daviaugusto.bff_agendador.infrastructure.dtos.out.EnderecoDTOResponse;
import com.daviaugusto.bff_agendador.infrastructure.dtos.out.TelefoneDTOResponse;
import com.daviaugusto.bff_agendador.infrastructure.dtos.out.UsuarioDTOResponse;
import com.daviaugusto.bff_agendador.infrastructure.dtos.out.ViaCepDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @PostMapping
    UsuarioDTOResponse salvarUsuario(@RequestBody UsuarioDTORequest usuarioDTO);

    @PostMapping("/login")
    String login (@RequestBody LoginDTORequest usuarioDTO);

    @GetMapping
    UsuarioDTOResponse buscarUsuarioPorEmail(@RequestParam("email") String email,
                                             @RequestHeader("Authorization") String token);

    @DeleteMapping
    void deletarUsuarioPorEmail(@RequestParam("email") String email,
                                @RequestHeader("Authorization") String token);

    @PutMapping
    UsuarioDTOResponse atualizarDadosUsuario(@RequestBody UsuarioDTORequest usuarioDTO,
                                             @RequestHeader("Authorization") String token);

    @PutMapping("/telefone/{id}")
    TelefoneDTOResponse atualizarTelefone(@PathVariable Long id,
                                          @RequestBody TelefoneDTORequest telefoneDTO,
                                          @RequestHeader("Authorization") String token);

    @PutMapping("/endereco/{id}")
    EnderecoDTOResponse atualizarEndereco(@PathVariable Long id,
                                          @RequestBody EnderecoDTORequest enderecoDTO,
                                          @RequestHeader("Authorization") String token);

    @PostMapping("/endereco")
    EnderecoDTOResponse inserirEndereco(@RequestBody EnderecoDTORequest enderecoDTO,
                                        @RequestHeader("Authorization") String token);

    @PostMapping("/telefone")
    TelefoneDTOResponse inserirTelefone(@RequestBody TelefoneDTORequest telefoneDTO,
                                        @RequestHeader("Authorization") String token);


    @GetMapping("/endereco/{cep}")
    ViaCepDTOResponse buscarCep(@PathVariable("cep") String cep);
}
