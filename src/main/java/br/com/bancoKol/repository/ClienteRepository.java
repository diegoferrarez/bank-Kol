package br.com.bancoKol.repository;

import br.com.bancoKol.domain.entities.Clients;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ClienteRepository extends ReactiveMongoRepository<Clients, String> {

    Mono<Clients> findById(String id);

    @Query("{numberAgency:'?0'}")
    Flux<Clients> findByNumberAgency(String numberAgency);

//    @Query("{$and : {numberAccount: { $in : ?0 }}, {numberAgency: { $in : ?1 }}}")
//    Mono<Clients> findByNumberAgencyAndNumberAccount(String numberAccount, String numberAgency);
}
