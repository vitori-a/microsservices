package com.microsservices.mscartoes.application;

import com.microsservices.mscartoes.domain.Cartao;
import com.microsservices.mscartoes.domain.ClienteCartao;
import com.microsservices.mscartoes.infra.repository.CartaoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cartoes")
@RequiredArgsConstructor
@Slf4j
public class CartoesResource {

    private final CartaoService service;
    private final ClienteCartaoService clienteCartaoService;
    private final CartaoRepository clienteRepository;

    @GetMapping
    public String status() {
        return "ok";
    }

    @GetMapping(params = "renda")
    public ResponseEntity<List<Cartao>> getCartoesRendaAteh(@RequestParam("renda") Long renda) {
        List<Cartao> list = service.getCartoesRendaMenorIgual(renda);
        return ResponseEntity.ok(list);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CartoesPorClienteResponse>> getCartoesByCliente(@RequestParam("cpf") String cpf){
        List<ClienteCartao> lista = clienteCartaoService.listarByCpf(cpf);
        List<CartoesPorClienteResponse> resultList = lista.stream()
                .map(CartoesPorClienteResponse::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resultList);
    }

    @PostMapping
    public ResponseEntity save(@RequestBody CartaoSaveRequest request){
        var cartao = request.toModel();
        service.save(cartao);
        return ResponseEntity.status(HttpStatus.CREATED).build();


    }

}