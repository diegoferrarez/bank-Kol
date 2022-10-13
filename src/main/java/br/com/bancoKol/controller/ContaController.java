package br.com.bancoKol.controller;

import br.com.bancoKol.controller.dto.Clients.Response.ClientsResponse;
import br.com.bancoKol.controller.dto.ContaCorrente.Response.ContaResponse;
import br.com.bancoKol.repository.ClienteRepository;
import br.com.bancoKol.service.ContasService;
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
@RequestMapping("v1/contas")
@RequiredArgsConstructor
public class ContaController {

    @Autowired
    private ContasService service;

    @ApiOperation("Realiza a busca das contas no banco")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<ContaResponse> AllContas(){
        return service.getAllContas();
    }

    @GetMapping("/{numberAgency}")
    @ResponseStatus(HttpStatus.FOUND)
    public Flux<ContaResponse> allContasAgencia(@PathVariable String numberAgency){
        return service.getAllContasAgencia(numberAgency);
    }

}


