package br.com.bancoKol.service.impl;

import br.com.bancoKol.controller.dto.Clients.Request.ClientsRequest;
import br.com.bancoKol.controller.dto.Clients.Response.ClientsResponse;
import br.com.bancoKol.domain.entities.*;
import br.com.bancoKol.domain.enums.StatusAccount;
import br.com.bancoKol.repository.ClienteRepository;
import br.com.bancoKol.service.ClientesService;
import br.com.bancoKol.utils.ConstantUtils;
import br.com.bancoKol.utils.CriptoUtil;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
@Slf4j
public class ClientesServiceImpl implements ClientesService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private final EnviaEmailService enviaEmailService;

    public ClientesServiceImpl(ClienteRepository repository, EnviaEmailService enviaEmailService) {
        this.repository = repository;
        this.enviaEmailService = enviaEmailService;
    }

    @Override
    @Transactional
    public Flux<ClientsResponse> findAll() {
        return repository.findAll().map(ClientsResponse::converter);
    }

    @Override
    @Transactional
    public Mono<ClientsResponse> findById(String id) {
        log.info("Busca realizada para encontrar o documento: " + id);
        return repository.findById(id).map(ClientsResponse::converter);
    }

    @Override
    @Transactional
    public Mono<ClientsResponse> salvar(ClientsRequest dto, String idColaborator, String usernameColaborator) {
        Clients clients = loadCadastro(dto);
        clients.setDataPersonal(encrypt(dto));
        clients.setStatusAccount(StatusAccount.ATIVO);
        enviaEmailService.enviar(dto.getDataPersonal().getEmail(), ConstantUtils.TITULO_CONTA_CRIADA,
                ConstantUtils.CONTA_CRIADA);

        log.info("Cadastro realizado. Identificação da conta: " + clients.getAccount());
        log.info("Cadastro realizado. Identificação da agência: " + clients.getAgency());
        log.info("Identificação da matrícula do funcionário: " + idColaborator + " - " + usernameColaborator);

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
            enviaEmailService.enviar(dto.getDataPersonal().getEmail(), ConstantUtils.TITULO_ALTERACAO_CONTA,
                    ConstantUtils.ALTERACAO_REALIZADA);
            return modelMapper.map(clients, ClientsResponse.class);
        });
    }

    @Override
    @Transactional
    public Mono<ClientsResponse> inactive(String id, ClientsRequest dto) {

        ModelMapper modelMapper = new ModelMapper();
        return repository.findById(id).map(c -> {
            Clients clients = loadCadastro(dto);
            clients.setId(id);
            clients.setStatusAccount(StatusAccount.CANCELADA);
            clients.setContaCorrente(ContaCorrente.builder()
                    .saldo(BigDecimal.valueOf(0))
                    .emprestimoLiberado(BigDecimal.valueOf(0))
                    .chequeEspecial(BigDecimal.valueOf(0))
                    .build());
            repository.save(clients).subscribe();
            enviaEmailService.enviar(dto.getDataPersonal().getEmail(), ConstantUtils.TITULO_CANCELAMENTO_CONTA,
                    ConstantUtils.CANCELAMENTO_REALIZADO);
            return modelMapper.map(clients, ClientsResponse.class);
        });
    }

    @Override
    @Transactional
    public Mono<Void> delete(final String id) {
        return repository.deleteById(id);
    }

    private Clients loadCadastro(ClientsRequest dto) {
        return Clients.builder()
                .agency(dto.getAgency())
                .account(dto.getAccount())
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
                .contaCorrente(ContaCorrente.builder()
                        .saldo(dto.getContaCorrente().getSaldo())
                        .chequeEspecial(dto.getContaCorrente().getChequeEspecial())
                        .emprestimoLiberado(dto.getContaCorrente().getEmprestimoLiberado())
                        .build())
                .stateAccount(dto.getStateAccount())
                .build();
    }

    private DataPersonal encrypt(ClientsRequest dto) {

        String name = CriptoUtil.criptografarBase64(dto.getDataPersonal().getName());
        String age = CriptoUtil.criptografarBase64(dto.getDataPersonal().getAge());
        String city = CriptoUtil.criptografarBase64(dto.getDataPersonal().getAddress().getCity());
        String country = CriptoUtil.criptografarBase64(dto.getDataPersonal().getAddress().getCountry());
        String state = CriptoUtil.criptografarBase64(dto.getDataPersonal().getAddress().getState());
        String complement = CriptoUtil.criptografarBase64(dto.getDataPersonal().getAddress().getComplement());
        String number = CriptoUtil.criptografarBase64(dto.getDataPersonal().getAddress().getNumber());
        String email = CriptoUtil.criptografarBase64(dto.getDataPersonal().getEmail());
        String docNumber = CriptoUtil.criptografarBase64(dto.getDataPersonal().getDocuments().getNumber());
        String doc = CriptoUtil.criptografarBase64(dto.getDataPersonal().getDocuments().getTypeDocument());

        return DataPersonal.builder()
                .name(name)
                .age(age)
                .address(Address.builder()
                        .country(country)
                        .state(state)
                        .city(city)
                        .complement(complement)
                        .number(number)
                        .build())
                .email(email)
                .gender(dto.getDataPersonal().getGender())
                .numberPhone(dto.getDataPersonal().getNumberPhone())
                .documents(Documents.builder()
                        .number(docNumber)
                        .typeDocument(doc)
                        .build())
                .build();
    }

}
