package br.com.bancoKol.controller.dto.Clients.Response;

import br.com.bancoKol.domain.entities.*;
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
public class ClientsResponse implements Serializable {

    private String id;
    private String agency;
    private String account;
    private DataPersonal dataPersonal;
    private AccountType accountType;
    private ContaCorrente contaCorrente;
    private StatusAccount statusAccount;
    private String stateAccount;

    public static ClientsResponse converter(Clients c){
        return ClientsResponse.builder()
                .id(c.getId())
                .agency(c.getAgency())
                .account(c.getAccount())
                .dataPersonal(c.getDataPersonal())
                .accountType(c.getAccountType())
                .contaCorrente(c.getContaCorrente())
                .statusAccount(c.getStatusAccount())
                .stateAccount(c.getStateAccount())
                .build();
    }
}
