package com.daviaugusto.bff_agendador.business;

import com.daviaugusto.bff_agendador.clients.NotificacaoClient;
import com.daviaugusto.bff_agendador.infrastructure.dtos.in.TarefaDTORequest;
import com.daviaugusto.bff_agendador.infrastructure.dtos.out.TarefaDTOResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificacaoService {

    @Autowired
    NotificacaoClient notificacaoClient;


    public void enviarEmail(TarefaDTOResponse tarefaDTOResponse){
        notificacaoClient.enviarEmail(tarefaDTOResponse);
    }

}
