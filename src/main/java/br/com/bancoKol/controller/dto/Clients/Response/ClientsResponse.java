package br.com.bancoKol.controller.dto.Clients.Response;

import br.com.bancoKol.domain.entities.*;
import br.com.bancoKol.domain.enums.AccountType;
import br.com.bancoKol.domain.enums.StatusAccount;
import br.com.bancoKol.utils.CriptoUtil;
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
                .dataPersonal(personal(c))
                .accountType(c.getAccountType())
                .contaCorrente(c.getContaCorrente())
                .statusAccount(c.getStatusAccount())
                .stateAccount(c.getStateAccount())
                .build();
    }
    private static DataPersonal personal(Clients c){

        String name = CriptoUtil.descriptografarBase64(c.getDataPersonal().getName());
        String age = CriptoUtil.descriptografarBase64(c.getDataPersonal().getAge());
        String city = CriptoUtil.descriptografarBase64(c.getDataPersonal().getAddress().getCity());
        String country = CriptoUtil.descriptografarBase64(c.getDataPersonal().getAddress().getCountry());
        String state = CriptoUtil.descriptografarBase64(c.getDataPersonal().getAddress().getState());
        String complement = CriptoUtil.descriptografarBase64(c.getDataPersonal().getAddress().getComplement());
        String number = CriptoUtil.descriptografarBase64(c.getDataPersonal().getAddress().getNumber());
        String email = CriptoUtil.descriptografarBase64(c.getDataPersonal().getEmail());
        String docNumber = CriptoUtil.descriptografarBase64(c.getDataPersonal().getDocuments().getNumber());
        String doc = CriptoUtil.descriptografarBase64(c.getDataPersonal().getDocuments().getTypeDocument());

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
                .gender(c.getDataPersonal().getGender())
                .numberPhone(c.getDataPersonal().getNumberPhone())
                .documents(Documents.builder()
                        .number(docNumber)
                        .typeDocument(doc)
                        .build())
                .build();
    }
}
