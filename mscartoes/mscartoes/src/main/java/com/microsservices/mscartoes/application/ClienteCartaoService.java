package com.microsservices.mscartoes.application;

import com.microsservices.mscartoes.domain.ClienteCartao;
import com.microsservices.mscartoes.infra.repository.ClienteCartaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteCartaoService {

    private final ClienteCartaoRepository repository;

    public List<ClienteCartao> listarByCpf(String cpf){
        return repository.findByCpf(cpf);
    }
}
