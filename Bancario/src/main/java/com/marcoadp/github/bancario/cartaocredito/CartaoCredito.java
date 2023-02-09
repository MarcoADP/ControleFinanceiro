package com.marcoadp.github.bancario.cartaocredito;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cartao_credito")
@Getter
@NoArgsConstructor
public class CartaoCredito {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String usuarioCpf;

    @NotBlank
    private String banco;

    @NotBlank
    private String descricao;

    @NotNull
    private Integer diaFechamento;

    @NotNull
    private Integer diaVencimento;

    @NotNull
    private BigDecimal limite;

    @NotNull
    private BigDecimal faturaParcial;

    public CartaoCredito(
            String usuarioCpf,
            String banco,
            String descricao,
            Integer diaFechamento,
            Integer diaVencimento,
            BigDecimal limite
    ) {
        this.usuarioCpf = usuarioCpf;
        this.banco = banco;
        this.descricao = descricao;
        this.diaFechamento = diaFechamento;
        this.diaVencimento = diaVencimento;
        this.limite = limite;
        this.faturaParcial = BigDecimal.ZERO;
    }

    public void update(
            String usuarioCpf,
            String banco,
            String descricao,
            Integer diaFechamento,
            Integer diaVencimento,
            BigDecimal limite
    ) {
        this.usuarioCpf = usuarioCpf;
        this.banco = banco;
        this.descricao = descricao;
        this.diaFechamento = diaFechamento;
        this.diaVencimento = diaVencimento;
        this.limite = limite;
        this.faturaParcial = BigDecimal.ZERO;
    }

    public void updateFaturaParcial(BigDecimal valor) {
        this.faturaParcial = this.getFaturaParcial().add(valor);
    }
}
