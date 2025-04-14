package com.daviaugusto.bff_agendador.clients;


import com.daviaugusto.bff_agendador.infrastructure.dtos.in.TarefaDTORequest;
import com.daviaugusto.bff_agendador.infrastructure.dtos.out.TarefaDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notificacao", url = "${notificacao.url}")
public interface NotificacaoClient {


    @PostMapping
    void enviarEmail(@RequestBody TarefaDTOResponse tarefaDTO);

}
