package br.com.bancoKol.service;

import br.com.bancoKol.controller.dto.Clients.Request.ClientsRequest;
import br.com.bancoKol.controller.dto.Clients.Response.ClientsResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientesService {
    Flux<ClientsResponse> findAll();

    Mono<ClientsResponse> findById(String id);

    Mono<ClientsResponse> salvar(ClientsRequest request, String idColaborator, String usernameColaborator);

    Mono<ClientsResponse> alterar(String id, ClientsRequest request);

    Mono<ClientsResponse> inactive(String id, ClientsRequest request);

    Mono<Void> delete(String id);

}
