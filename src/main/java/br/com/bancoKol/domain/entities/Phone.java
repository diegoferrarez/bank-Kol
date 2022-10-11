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
public class Phone implements Serializable {
    private String internationalCode;
    private String localCode;
    private String number;
}
