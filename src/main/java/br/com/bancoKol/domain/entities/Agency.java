package br.com.bancoKol.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Agency implements Serializable {
    private String numberAgency;
    private String numberAccount;
    private String stateAccount;
}
