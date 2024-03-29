package br.com.bancoKol.domain.entities;


import br.com.bancoKol.domain.enums.AccountType;
import br.com.bancoKol.domain.enums.StatusAccount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "ClienteBancario")
public class Clients implements Serializable {

    @Id
    private String id;
    private String agency;
    private String account;
    private DataPersonal dataPersonal;
    private AccountType accountType;
    private ContaCorrente contaCorrente;
    private StatusAccount statusAccount;
    private String stateAccount;

}
