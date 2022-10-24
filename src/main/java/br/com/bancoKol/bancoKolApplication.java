package br.com.bancoKol;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@Slf4j
@EnableWebFlux
@SpringBootApplication
public class bancoKolApplication {

    public static void main(String[] args) {
        SpringApplication.run(bancoKolApplication.class, args);
    }

}