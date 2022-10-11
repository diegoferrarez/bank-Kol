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
public class Address implements Serializable {

    private String country;
    private String state;
    private String city;
    private String complement;
    private String number;

}
