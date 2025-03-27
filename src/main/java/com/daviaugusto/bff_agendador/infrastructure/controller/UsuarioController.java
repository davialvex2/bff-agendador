package com.daviaugusto.bff_agendador.infrastructure.controller;


import com.daviaugusto.bff_agendador.business.UsuarioService;
import com.daviaugusto.bff_agendador.infrastructure.dtos.in.EnderecoDTORequest;
import com.daviaugusto.bff_agendador.infrastructure.dtos.in.LoginDTORequest;
import com.daviaugusto.bff_agendador.infrastructure.dtos.in.TelefoneDTORequest;
import com.daviaugusto.bff_agendador.infrastructure.dtos.in.UsuarioDTORequest;
import com.daviaugusto.bff_agendador.infrastructure.dtos.out.EnderecoDTOResponse;
import com.daviaugusto.bff_agendador.infrastructure.dtos.out.TelefoneDTOResponse;
import com.daviaugusto.bff_agendador.infrastructure.dtos.out.UsuarioDTOResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuário", description = "Cadastro e login de usuários")
public class UsuarioController {

    private final UsuarioService usuarioService;


    @PostMapping
    @Operation(summary = "Salvar usuário", description = "Cria um nome usuário")
    @ApiResponse(responseCode = "200", description = "Usuário salvo com sucesso")
    @ApiResponse(responseCode = "400", description = "Usuário já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<UsuarioDTOResponse> salvarUsuario(@RequestBody UsuarioDTORequest usuarioDTO){
        return ResponseEntity.ok(usuarioService.salvarUsuario(usuarioDTO));
    }

    @PostMapping("/login")
    @Operation(summary = "Logar Usuário", description = "Faz o login do usuário")
    @ApiResponse(responseCode = "200", description = "Usuário logado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public String login (@RequestBody LoginDTORequest usuarioDTO){
        return usuarioService.login(usuarioDTO);
    }

    @GetMapping
    @Operation(summary = "Busca dados do usuário por email", description = "Faz a busca dos dados do usuário pelo email")
    @ApiResponse(responseCode = "200", description = "Usuario encontrado")
    @ApiResponse(responseCode = "404", description = "usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<UsuarioDTOResponse> buscarUsuarioPorEmail(@RequestParam("email") String email,
                                                                    @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email, token));
    }

    @DeleteMapping
    @Operation(summary = "Deleta usuário", description = "Deleta usuário pelo email")
    @ApiResponse(responseCode = "200", description = "Usuario excluido com sucesso")
    @ApiResponse(responseCode = "404", description = "usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<Void> deletarUsuarioPorEmail(@RequestParam("email") String email,
                                                       @RequestHeader("Authorization") String token){
        usuarioService.deletarUsuarioPorEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Atualiza dados do usuário", description = "Atualizar dados de usuário")
    @ApiResponse(responseCode = "200", description = "Usuario atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<UsuarioDTOResponse> atualizarDadosUsuario(@RequestBody UsuarioDTORequest usuarioDTO,
                                                                    @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.atualizarDadosUsuario(token, usuarioDTO));
    }

    @PutMapping("/telefone/{id}")
    @Operation(summary = "Atualiza telefone", description = "Faz atualização de telefone por id")
    @ApiResponse(responseCode = "200", description = "Telefone atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "telefone não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<TelefoneDTOResponse> atualizarTelefone(@PathVariable Long id,
                                                                 @RequestBody TelefoneDTORequest telefoneDTO,
                                                                 @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.atualizarDadosTelefone(id, telefoneDTO, token));
    }

    @PutMapping("/endereco/{id}")
    @Operation(summary = "Atualiza endereço", description = "Faz atualização do endereço por id")
    @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Endereço não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<EnderecoDTOResponse> atualizarEndereco(@PathVariable Long id,
                                                                 @RequestBody EnderecoDTORequest enderecoDTO,
                                                                 @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.atualizarEndereco(id, enderecoDTO, token));

    }

    @PostMapping("/endereco")
    @Operation(summary = "Cria um novo endereço", description = "Cadastrada um novo endereço")
    @ApiResponse(responseCode = "200", description = "Endereço cadastrado com sucesso")
    @ApiResponse(responseCode = "400", description = "Endereço já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<EnderecoDTOResponse> inserirEndereco(@RequestBody EnderecoDTORequest enderecoDTO,
                                                               @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.inserirEndereco(token, enderecoDTO));
    }

    @PostMapping("/telefone")
    @Operation(summary = "Cria um novo telefone", description = "Cadastra um novo telefone")
    @ApiResponse(responseCode = "200", description = "Telefone cadastrado com sucesso")
    @ApiResponse(responseCode = "40", description = "telefone já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<TelefoneDTOResponse> inserirTelefone(@RequestBody TelefoneDTORequest telefoneDTO,
                                                               @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.inserirTelefone(token, telefoneDTO));
    }

}
