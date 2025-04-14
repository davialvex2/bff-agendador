package com.daviaugusto.bff_agendador.business;

import com.daviaugusto.bff_agendador.clients.TarefaClient;
import com.daviaugusto.bff_agendador.infrastructure.StatusTarefaEnum;
import com.daviaugusto.bff_agendador.infrastructure.dtos.in.TarefaDTORequest;
import com.daviaugusto.bff_agendador.infrastructure.dtos.out.TarefaDTOResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class TarefaService {

    @Autowired
    private TarefaClient tarefaClient;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public TarefaDTOResponse gravarTarefa(TarefaDTORequest tarefaDTO, String token) {
        return tarefaClient.gravarTarefa(tarefaDTO, token);
    }

    public List<TarefaDTOResponse> buscarTarefaPeriodo(LocalDateTime min, LocalDateTime max, String token) {
        return tarefaClient.buscarPorPeriodo(min, max, token);
    }

    public List<TarefaDTOResponse> buscarTarefasPorEmail(String token) {
        return tarefaClient.buscarPorEmail(token);
    }

    public void deletarTarefa(String id, String token) {
      tarefaClient.deletarTarefa(id, token);
    }

    public TarefaDTOResponse atualizarStatus(String id, StatusTarefaEnum status, String token) {
        return tarefaClient.atualizarStatus(id, status, token);
    }


    public TarefaDTOResponse atualizarTarefa(String id, TarefaDTORequest dto, String token) {
     return tarefaClient.atualizarTarefa(id, dto, token);
    }

}
