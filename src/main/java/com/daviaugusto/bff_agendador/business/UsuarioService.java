package com.daviaugusto.bff_agendador.business;

import com.daviaugusto.bff_agendador.clients.UsuarioClient;
import com.daviaugusto.bff_agendador.infrastructure.dtos.in.EnderecoDTORequest;
import com.daviaugusto.bff_agendador.infrastructure.dtos.in.LoginDTORequest;
import com.daviaugusto.bff_agendador.infrastructure.dtos.in.TelefoneDTORequest;
import com.daviaugusto.bff_agendador.infrastructure.dtos.in.UsuarioDTORequest;
import com.daviaugusto.bff_agendador.infrastructure.dtos.out.EnderecoDTOResponse;
import com.daviaugusto.bff_agendador.infrastructure.dtos.out.TelefoneDTOResponse;
import com.daviaugusto.bff_agendador.infrastructure.dtos.out.UsuarioDTOResponse;
import com.daviaugusto.bff_agendador.infrastructure.dtos.out.ViaCepDTOResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

  @Autowired
  private UsuarioClient usuarioClient;

    public String login(LoginDTORequest usuarioDTO){

        return usuarioClient.login(usuarioDTO);
    }

    public UsuarioDTOResponse salvarUsuario(UsuarioDTORequest usuarioDTO){
        return usuarioClient.salvarUsuario(usuarioDTO);
    }

    public UsuarioDTOResponse buscarUsuarioPorEmail(String email, String token){
        return usuarioClient.buscarUsuarioPorEmail(email, token);
    }

    public void deletarUsuarioPorEmail(String email, String token){
        usuarioClient.deletarUsuarioPorEmail(email, token);
    }

    public UsuarioDTOResponse atualizarDadosUsuario(String token, UsuarioDTORequest usuarioDTO){
    return usuarioClient.atualizarDadosUsuario(usuarioDTO, token);
    }

    public EnderecoDTOResponse atualizarEndereco(Long id, EnderecoDTORequest enderecoDTO, String token){
        return usuarioClient.atualizarEndereco(id, enderecoDTO, token);
    }

    public TelefoneDTOResponse atualizarDadosTelefone(Long id, TelefoneDTORequest telefoneDTO, String token){
    return usuarioClient.atualizarTelefone(id, telefoneDTO, token);
    }

    public EnderecoDTOResponse inserirEndereco(String token, EnderecoDTORequest enderecoDTO){
        return usuarioClient.inserirEndereco(enderecoDTO, token);
    }

    public TelefoneDTOResponse inserirTelefone(String token, TelefoneDTORequest telefoneDTO){
        return usuarioClient.inserirTelefone(telefoneDTO, token);
    }

    public ViaCepDTOResponse buscarCep(String cep){
        return usuarioClient.buscarCep(cep);
    }
}

