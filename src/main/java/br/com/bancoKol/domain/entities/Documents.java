package br.com.bancoKol.domain.entities;

import br.com.bancoKol.domain.enums.TypeDocument;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Documents implements Serializable {
    private String number;
    private TypeDocument typeDocument;
}
