package br.com.bancoKol.domain.entities;

import br.com.bancoKol.domain.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataPersonal implements Serializable {

    private String name;
    private Integer age;
    private Address address;
    private String email;
    private Gender gender;
    private List<Phone> numberPhone;
    private List<Documents> documents;
}
