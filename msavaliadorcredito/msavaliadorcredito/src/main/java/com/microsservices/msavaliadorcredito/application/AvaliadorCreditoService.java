package com.microsservices.msavaliadorcredito.application;

import com.microsservices.msavaliadorcredito.application.ex.DadosClienteNotFoundException;
import com.microsservices.msavaliadorcredito.application.ex.ErroComunicacaoMicrosservicesException;
import com.microsservices.msavaliadorcredito.domain.model.CartaoCliente;
import com.microsservices.msavaliadorcredito.domain.model.DadosCliente;
import com.microsservices.msavaliadorcredito.domain.model.SituacaoCliente;
import com.microsservices.msavaliadorcredito.infra.clients.CartoesResourceClient;
import com.microsservices.msavaliadorcredito.infra.clients.ClienteResourceClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {

    private final ClienteResourceClient clienteResourceClient;
    private final CartoesResourceClient cartoesResourceClient;

    public SituacaoCliente obterSituacaoCliente(String cpf)
            throws DadosClienteNotFoundException, ErroComunicacaoMicrosservicesException{

        try {
            ResponseEntity<DadosCliente> dadosClienteResponse = clienteResourceClient.dadosCliente(cpf);
            ResponseEntity<List<CartaoCliente>> cartaoResponse = cartoesResourceClient.dadosCartaoCliente(cpf);
            return SituacaoCliente.builder()
                    .cliente(dadosClienteResponse.getBody())
                    .cartoes(cartaoResponse.getBody())
                    .build();
        } catch (FeignException.FeignClientException e) {
           int status = e.status();
           if(HttpStatus.NOT_FOUND.value() == status) {
                throw new DadosClienteNotFoundException();
           }
           throw new ErroComunicacaoMicrosservicesException(e.getMessage(), status);
        }
    }
}
