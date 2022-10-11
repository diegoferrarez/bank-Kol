package br.com.bancoKol.service.impl;

import br.com.bancoKol.controller.dto.Clients.Request.ClientsRequest;
import br.com.bancoKol.controller.dto.Clients.Response.ClientsResponse;
import br.com.bancoKol.domain.entities.*;
import br.com.bancoKol.domain.enums.StatusAccount;
import br.com.bancoKol.repository.ClienteRepository;
import br.com.bancoKol.service.ClientesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
public class ClientesServiceImpl implements ClientesService {

    @Autowired
    private ClienteRepository repository;

    @Override
    @Transactional
    public Flux<ClientsResponse> findAll() {
        return repository.findAll().map(ClientsResponse::converter);
    }

    @Override
    @Transactional
    public Mono<ClientsResponse> findById(String id) {
        return repository.findById(id).map(ClientsResponse::converter);
    }

    @Override
    @Transactional
    public Mono<ClientsResponse> salvar(ClientsRequest dto) {
        Clients clients = loadCadastro(dto);
        clients.setStatusAccount(StatusAccount.ATIVO);
        return repository.save(clients).map(ClientsResponse::converter);
    }

    @Override
    @Transactional
    public Mono<ClientsResponse> alterar(String id, ClientsRequest dto) {

        ModelMapper modelMapper = new ModelMapper();
        return repository.findById(id).map(c -> {
            Clients clients = loadCadastro(dto);
            clients.setId(id);
            clients.setStatusAccount(c.getStatusAccount());
            repository.save(clients).subscribe();
            return modelMapper.map(clients, ClientsResponse.class);
        });
    }

    @Override
    @Transactional
    public Mono<ClientsResponse> inactive(String id, ClientsRequest dto) {

        ModelMapper modelMapper = new ModelMapper();
        return repository.findById(id).map(c-> {
            Clients clients = loadCadastro(dto);
            clients.setId(id);
            clients.setStatusAccount(StatusAccount.CANCELADA);
            clients.setContaCorrente(ContaCorrente.builder()
                    .saldo(BigDecimal.valueOf(0))
                    .emprestimoLiberado(BigDecimal.valueOf(0))
                    .chequeEspecial(BigDecimal.valueOf(0))
                    .build());
            repository.save(clients).subscribe();
            return modelMapper.map(clients, ClientsResponse.class);
        });
    }

    @Override
    @Transactional
    public Mono<Void> delete(final String id) {
        return repository.deleteById(id);
    }

    private Clients loadCadastro(ClientsRequest dto){
        return Clients.builder()
                .dataPersonal(DataPersonal.builder()
                        .name(dto.getDataPersonal().getName())
                        .age(dto.getDataPersonal().getAge())
                        .address(Address.builder()
                                .country(dto.getDataPersonal().getAddress().getCountry())
                                .state(dto.getDataPersonal().getAddress().getState())
                                .city(dto.getDataPersonal().getAddress().getCity())
                                .complement(dto.getDataPersonal().getAddress().getComplement())
                                .number(dto.getDataPersonal().getAddress().getNumber())
                                .build())
                        .email(dto.getDataPersonal().getEmail())
                        .gender(dto.getDataPersonal().getGender())
                        .numberPhone(dto.getDataPersonal().getNumberPhone())
                        .documents(dto.getDataPersonal().getDocuments())
                        .build())
                .accountType(dto.getAccountType())
                .agencia(Agency.builder()
                        .numberAgency(dto.getAgencia().getNumberAgency())
                        .numberAccount(dto.getAgencia().getNumberAccount())
                        .stateAccount(dto.getAgencia().getStateAccount())
                        .build())
                .contaCorrente(ContaCorrente.builder()
                        .saldo(dto.getContaCorrente().getSaldo())
                        .chequeEspecial(dto.getContaCorrente().getChequeEspecial())
                        .emprestimoLiberado(dto.getContaCorrente().getEmprestimoLiberado())
                        .build())
                .build();
    }
}
