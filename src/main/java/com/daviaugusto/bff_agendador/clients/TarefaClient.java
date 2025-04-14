package com.daviaugusto.bff_agendador.clients;

import com.daviaugusto.bff_agendador.infrastructure.StatusTarefaEnum;
import com.daviaugusto.bff_agendador.infrastructure.dtos.in.TarefaDTORequest;
import com.daviaugusto.bff_agendador.infrastructure.dtos.out.TarefaDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "Tarefa", url = "${agendador-tarefa.url}")
public interface TarefaClient {

    @PostMapping
    TarefaDTOResponse gravarTarefa(@RequestBody TarefaDTORequest tarefaDTO, @RequestHeader("Authorization") String token);

    @GetMapping("/eventos")
    List<TarefaDTOResponse> buscarPorPeriodo(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataMin,
                                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataMax,
                                             @RequestHeader("Authorization") String token);

    @GetMapping
    List<TarefaDTOResponse> buscarPorEmail(@RequestHeader("Authorization") String token);

    @DeleteMapping("{id}")
    void deletarTarefa(@PathVariable String id,
                                              @RequestHeader("Authorization") String token);

    @PatchMapping
    TarefaDTOResponse atualizarStatus(@RequestParam("id") String id,
                                      @RequestParam("status") StatusTarefaEnum status,
                                      @RequestHeader("Authorization") String token);

    @PutMapping
    TarefaDTOResponse atualizarTarefa(@RequestParam("id") String id,
                                      @RequestBody TarefaDTORequest tarefaDTO,
                                      @RequestHeader("Authorization") String token);
}
