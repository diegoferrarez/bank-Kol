package br.com.bancoKol.service.impl;

import br.com.bancoKol.controller.dto.Clients.Response.ClientsResponse;
import br.com.bancoKol.controller.dto.ContaCorrente.Response.ContaResponse;
import br.com.bancoKol.repository.ClienteRepository;
import br.com.bancoKol.service.ContasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    public Flux<ContaResponse> getAllContasAgencia(String numberAgency) {
        return repository.findByNumberAgency(numberAgency).map(ContaResponse::converterContaCorrente);
    }


}

