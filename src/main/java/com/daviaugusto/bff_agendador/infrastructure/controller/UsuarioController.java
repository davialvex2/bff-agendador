package com.daviaugusto.bff_agendador.infrastructure.controller;


import com.daviaugusto.bff_agendador.business.UsuarioService;
import com.daviaugusto.bff_agendador.infrastructure.dtos.in.EnderecoDTORequest;
import com.daviaugusto.bff_agendador.infrastructure.dtos.in.LoginDTORequest;
import com.daviaugusto.bff_agendador.infrastructure.dtos.in.TelefoneDTORequest;
import com.daviaugusto.bff_agendador.infrastructure.dtos.in.UsuarioDTORequest;
import com.daviaugusto.bff_agendador.infrastructure.dtos.out.EnderecoDTOResponse;
import com.daviaugusto.bff_agendador.infrastructure.dtos.out.TelefoneDTOResponse;
import com.daviaugusto.bff_agendador.infrastructure.dtos.out.UsuarioDTOResponse;
import com.daviaugusto.bff_agendador.infrastructure.dtos.out.ViaCepDTOResponse;
import com.daviaugusto.bff_agendador.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuário", description = "Cadastro e login de usuários")
@SecurityRequirement(name = SecurityConfig.SECURITY_SHEME)
public class UsuarioController {

    private final UsuarioService usuarioService;


    @PostMapping
    @Operation(summary = "Salvar usuário", description = "Cria um nome usuário")
    @ApiResponse(responseCode = "200", description = "Usuário salvo com sucesso")
    @ApiResponse(responseCode = "409", description = "Usuário já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<UsuarioDTOResponse> salvarUsuario(@RequestBody UsuarioDTORequest usuarioDTO){
        return ResponseEntity.ok(usuarioService.salvarUsuario(usuarioDTO));
    }

    @PostMapping("/login")
    @Operation(summary = "Logar Usuário", description = "Faz o login do usuário")
    @ApiResponse(responseCode = "200", description = "Usuário logado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public String login (@RequestBody LoginDTORequest usuarioDTO){
        return usuarioService.login(usuarioDTO);
    }

    @GetMapping
    @Operation(summary = "Busca dados do usuário por email", description = "Faz a busca dos dados do usuário pelo email")
    @ApiResponse(responseCode = "200", description = "Usuario encontrado")
    @ApiResponse(responseCode = "403", description = "usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<UsuarioDTOResponse> buscarUsuarioPorEmail(@RequestParam("email") String email,
                                                                    @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email, token));
    }

    @DeleteMapping
    @Operation(summary = "Deleta usuário", description = "Deleta usuário pelo email")
    @ApiResponse(responseCode = "200", description = "Usuario excluido com sucesso")
    @ApiResponse(responseCode = "403", description = "usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<Void> deletarUsuarioPorEmail(@RequestParam("email") String email,
                                                       @RequestHeader("Authorization") String token){
        usuarioService.deletarUsuarioPorEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Atualiza dados do usuário", description = "Atualizar dados de usuário")
    @ApiResponse(responseCode = "200", description = "Usuario atualizado com sucesso")
    @ApiResponse(responseCode = "403", description = "usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<UsuarioDTOResponse> atualizarDadosUsuario(@RequestBody UsuarioDTORequest usuarioDTO,
                                                                    @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.atualizarDadosUsuario(token, usuarioDTO));
    }

    @PutMapping("/telefone/{id}")
    @Operation(summary = "Atualiza telefone", description = "Faz atualização de telefone por id")
    @ApiResponse(responseCode = "200", description = "Telefone atualizado com sucesso")
    @ApiResponse(responseCode = "403", description = "telefone não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<TelefoneDTOResponse> atualizarTelefone(@PathVariable Long id,
                                                                 @RequestBody TelefoneDTORequest telefoneDTO,
                                                                 @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.atualizarDadosTelefone(id, telefoneDTO, token));
    }

    @PutMapping("/endereco/{id}")
    @Operation(summary = "Atualiza endereço", description = "Faz atualização do endereço por id")
    @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso")
    @ApiResponse(responseCode = "403", description = "Endereço não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
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
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<EnderecoDTOResponse> inserirEndereco(@RequestBody EnderecoDTORequest enderecoDTO,
                                                               @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.inserirEndereco(token, enderecoDTO));
    }

    @PostMapping("/telefone")
    @Operation(summary = "Cria um novo telefone", description = "Cadastra um novo telefone")
    @ApiResponse(responseCode = "200", description = "Telefone cadastrado com sucesso")
    @ApiResponse(responseCode = "409", description = "telefone já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<TelefoneDTOResponse> inserirTelefone(@RequestBody TelefoneDTORequest telefoneDTO,
                                                               @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.inserirTelefone(token, telefoneDTO));
    }

    @GetMapping("/endereco/{cep}")
    @Operation(summary = "Busca endereço pelo CEP", description = "Busca de CEP")
    @ApiResponse(responseCode = "200", description = "Endereço encontrado com sucesso")
    @ApiResponse(responseCode = "400", description = "Os caracteres do CEP invalidos")
    public ResponseEntity<ViaCepDTOResponse> buscarCep(@PathVariable("cep") String cep) {
    return ResponseEntity.ok(usuarioService.buscarCep(cep));
    }

}
