package com.marcoadp.github.bancario.fatura;

import com.marcoadp.github.bancario.cartaocredito.CartaoCredito;
import com.marcoadp.github.bancario.movimentacao.Movimentacao;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Fatura {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cartao_credito_id")
    private CartaoCredito cartaoCredito;

    @NotBlank
    private String descricao;

    @NotBlank
    private FaturaSituacao situacao;

    @NotNull
    private LocalDate dataVencimento;

    @NotNull
    private LocalDate dataPagamento;

    @NotNull
    private BigDecimal valor;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "movimentacao_id")
    private Movimentacao movimentacao;

    public Fatura(
            CartaoCredito cartaoCredito,
            String descricao,
            LocalDate dataVencimento,
            LocalDate dataPagamento,
            BigDecimal valor,
            Movimentacao movimentacao
    ) {
        this.cartaoCredito = cartaoCredito;
        this.descricao = descricao;
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
        this.valor = valor;
        this.movimentacao = movimentacao;
    }
}
