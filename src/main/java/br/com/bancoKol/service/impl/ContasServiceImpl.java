package br.com.bancoKol.service.impl;

import br.com.bancoKol.controller.dto.ContaCorrente.Response.ContaResponse;
import br.com.bancoKol.repository.ClienteRepository;
import br.com.bancoKol.service.ContasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ContasServiceImpl implements ContasService {

    @Autowired
    private ClienteRepository repository;

    @Override
    public Flux<ContaResponse> getAllContas() {
        return repository.findAll().map(ContaResponse::converterContaCorrente);
    }

    @Override
    public Mono<ContaResponse> findBy(String numberAccount) {
        return repository.findByConta(numberAccount)
                .map(c -> ContaResponse.converterContaCorrente(c)); //não funcionou
    }
}

