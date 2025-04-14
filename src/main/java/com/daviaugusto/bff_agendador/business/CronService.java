package com.daviaugusto.bff_agendador.business;


import com.daviaugusto.bff_agendador.clients.NotificacaoClient;
import com.daviaugusto.bff_agendador.clients.TarefaClient;
import com.daviaugusto.bff_agendador.clients.UsuarioClient;
import com.daviaugusto.bff_agendador.infrastructure.StatusTarefaEnum;
import com.daviaugusto.bff_agendador.infrastructure.dtos.in.LoginDTORequest;
import com.daviaugusto.bff_agendador.infrastructure.dtos.out.TarefaDTOResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class CronService {

    @Autowired
    private TarefaService tarefaService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private NotificacaoService notificacaoService;


    @Value("${usuario.email}")
    private String email;

    @Value("${usuario.senha}")
    private String senha;




    @Scheduled(cron = "${cron.horario}")
    public void buscaTarefasProximaHora(){

        String token = login(paraLoginDTORequest());
        log.info("Iniciada busca de tarefas");
        LocalDateTime horaFutura = LocalDateTime.now().plusHours(1);
        LocalDateTime horaFuturaMaisCinco = LocalDateTime.now().plusHours(1).plusMinutes(5);
        List<TarefaDTOResponse> tarefas = tarefaService.buscarTarefaPeriodo(horaFutura, horaFuturaMaisCinco, token);
        log.info("Tarefas encontradas " + tarefas);
        tarefas.forEach(x -> {notificacaoService.enviarEmail(x);
            log.info("email enviado " + x.getEmailUsuario());
            tarefaService.atualizarStatus(x.getId(), StatusTarefaEnum.NOTIFICADO, token);});



    }

    public String login(LoginDTORequest dto){
        return usuarioService.login(dto);
    }


    public LoginDTORequest paraLoginDTORequest(){
        return LoginDTORequest.builder().email(email).senha(senha).build();
    }


}
