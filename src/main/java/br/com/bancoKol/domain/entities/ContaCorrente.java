package br.com.bancoKol.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContaCorrente implements Serializable {
    private BigDecimal saldo;
    private BigDecimal chequeEspecial;
    private BigDecimal emprestimoLiberado;
}
