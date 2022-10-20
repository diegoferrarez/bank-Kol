package br.com.bancoKol.controller.dto.ContaCorrente.Response;

import br.com.bancoKol.domain.entities.Agency;
import br.com.bancoKol.domain.entities.Clients;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContaResponse implements Serializable {

    private String id;
    private String agency;
    private String account;
    private String stateAccount;

    public static ContaResponse converterContaCorrente (Clients c){
        return ContaResponse.builder()
                .id(c.getId())
                .agency(c.getAgency())
                .account(c.getAccount())
                .stateAccount(c.getStateAccount())
                .build();
    }

}
