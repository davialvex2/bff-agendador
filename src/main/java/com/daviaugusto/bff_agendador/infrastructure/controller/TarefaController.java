package com.daviaugusto.bff_agendador.infrastructure.controller;


import com.daviaugusto.bff_agendador.business.TarefaService;
import com.daviaugusto.bff_agendador.infrastructure.StatusTarefaEnum;
import com.daviaugusto.bff_agendador.infrastructure.dtos.in.TarefaDTORequest;
import com.daviaugusto.bff_agendador.infrastructure.dtos.out.TarefaDTOResponse;
import com.daviaugusto.bff_agendador.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefa")
@Tag(name = "Agendador-Tarefas", description = "Cadastro de tarefas")
@SecurityRequirement(name = SecurityConfig.SECURITY_SHEME)
public class TarefaController {

    @Autowired
    public TarefaService tarefaServive;

    @PostMapping
    @Operation(summary = "Salvar tarefa", description = "Cria uma nova tarefa")
    @ApiResponse(responseCode = "200", description = "Tarefa salva com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<TarefaDTOResponse> gravarTarefa(@RequestBody TarefaDTORequest tarefaDTO,
                                                          @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefaServive.gravarTarefa(tarefaDTO, token));
    }

    @GetMapping("/eventos")
    @Operation(summary = "Busca uma lista de tarefas por período", description = "Faz a busca de tarefas por período")
    @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<List<TarefaDTOResponse>> buscarPorPeriodo(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataMin,
                                                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataMax,
                                                                    @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefaServive.buscarTarefaPeriodo(dataMin, dataMax, token));
    }

    @GetMapping
    @Operation(summary = "Busca uma lista de tarefas pelo email", description = "Faz a busca de tarefas por email")
    @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<List<TarefaDTOResponse>> buscarPorEmail(@RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefaServive.buscarTarefasPorEmail(token));
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Deleta uma tarefa", description = "Deleta uma tarefa pelo id")
    @ApiResponse(responseCode = "200", description = "Tarefa deletada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<Void> deletarTarefa(@PathVariable String id,
                                              @RequestHeader(name = "Authorization", required = false) String token){
        tarefaServive.deletarTarefa(id, token);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    @Operation(summary = "Atualiza o status da terefa", description = "Atualiza os status da tarefa")
    @ApiResponse(responseCode = "200", description = "Atualização realizada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<TarefaDTOResponse> atualizarStatus(@RequestParam("id") String id,
                                                             @RequestParam("status") StatusTarefaEnum status,
                                                             @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefaServive.atualizarStatus(id, status, token));
    }

    @PutMapping
    @Operation(summary = "Atualiza os dados de uma tarefa", description = "Atualiza os dados de uma tarafa pelo id")
    @ApiResponse(responseCode = "200", description = "Atualização realizada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<TarefaDTOResponse> atualizarTarefa(@RequestParam("id") String id,
                                                             @RequestBody TarefaDTORequest tarefaDTO,
                                                             @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefaServive.atualizarTarefa(id, tarefaDTO, token));
    }


}
