package com.microsservices.msavaliadorcredito.application;

import com.microsservices.msavaliadorcredito.domain.model.SituacaoCliente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {

    public SituacaoCliente obterSituacaoCliente(String cpf){
        //obterDadosCliente - MSCLIENTES
        //obter cartoes do cliente - MSCartoes
    }
}
