package br.com.bancoKol.service;

import br.com.bancoKol.controller.dto.ContaCorrente.Response.ContaResponse;
import reactor.core.publisher.Flux;

public interface ContasService {
    Flux<ContaResponse> getAllContas();

    Flux<ContaResponse> getAllContasAgencia(String numberAgency);
}
