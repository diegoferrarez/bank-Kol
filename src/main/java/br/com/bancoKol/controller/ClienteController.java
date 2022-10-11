package br.com.bancoKol.controller;

//import br.com.bancoKol.config.Kafka.ProducerKafka;
import br.com.bancoKol.controller.dto.Clients.Request.ClientsRequest;
import br.com.bancoKol.controller.dto.Clients.Response.ClientsResponse;
import br.com.bancoKol.repository.ClienteRepository;
import br.com.bancoKol.service.ClientesService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("v1/clientes")
public class ClienteController {

//    private final ProducerKafka topicProducer;

    @Autowired
    private ClientesService service;

    @Autowired
    private ClienteRepository repository;

    @ApiOperation(value = "Retorna todas as contas cadastradas")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<ClientsResponse> getAll(){
//        topicProducer.send("Mensagem de teste enviada");
        return service.findAll();
    }

    @ApiOperation(value = "Realiza a busca de uma conta por id")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<ClientsResponse> getById(@PathVariable String id){
        return service.findById(id);
    }

    @ApiOperation(value = "Criação de uma conta")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ClientsResponse> create(@RequestBody ClientsRequest clientsRequest){
//        topicProducer.send("Mensagem de teste enviada");
        return service.salvar(clientsRequest);
    }

    @ApiOperation(value="Alteração dos dados de uma conta por id.")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<ClientsResponse> update(@PathVariable String id, @RequestBody ClientsRequest request){
        return service.alterar(id, request);
    }

    @ApiOperation(value="Cancelamento de conta zerando os saldos")
    @PatchMapping("/cancel/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<ClientsResponse> accountInactive(@PathVariable String id, @RequestBody ClientsRequest request){
        return service.inactive(id, request);
    }

    @ApiOperation(value="Deletar uma determinada conta")
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<Void> deleteAccount(@PathVariable final String id){
        return service.delete(id);
    }
}
