package br.com.bancoKol.service;

import br.com.bancoKol.controller.dto.ContaCorrente.Response.ContaResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ContasService {
    Flux<ContaResponse> getAllContas();

    Mono<ContaResponse> findBy(String numberAccount);

}
