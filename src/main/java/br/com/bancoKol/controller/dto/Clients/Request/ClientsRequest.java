package br.com.bancoKol.controller.dto.Clients.Request;

import br.com.bancoKol.domain.entities.ContaCorrente;
import br.com.bancoKol.domain.entities.DataPersonal;
import br.com.bancoKol.domain.enums.AccountType;
import br.com.bancoKol.domain.enums.StatusAccount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientsRequest implements Serializable {
    private String id;
    private String agency;
    private String account;
    private DataPersonal dataPersonal;
    private AccountType accountType;
    private ContaCorrente contaCorrente;
    private StatusAccount statusAccount;
    private String stateAccount;
}
