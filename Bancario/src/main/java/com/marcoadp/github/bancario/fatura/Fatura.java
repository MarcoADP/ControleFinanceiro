package com.marcoadp.github.bancario.fatura;

import com.marcoadp.github.bancario.cartaocredito.CartaoCredito;
import com.marcoadp.github.bancario.movimentacao.Movimentacao;
import com.marcoadp.github.bancario.transacao.Transacao;
import com.marcoadp.github.bancario.transacao.TransacaoTipo;
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
    private LocalDate dataFechamento;

    @NotNull
    private LocalDate dataVencimento;

    private LocalDate dataPagamento;

    @NotNull
    private BigDecimal valor;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "movimentacao_id")
    private Movimentacao movimentacao;

    public Fatura(
            CartaoCredito cartaoCredito,
            Integer mesReferencia,
            Integer anoReferencia
    ) {
        this.cartaoCredito = cartaoCredito;
        this.descricao = String.format("Fatura do cartão %s de %d/%d", cartaoCredito.getDescricao(), mesReferencia, anoReferencia);
        this.dataFechamento = LocalDate.of(anoReferencia, mesReferencia, cartaoCredito.getDiaFechamento());
        this.dataVencimento = LocalDate.of(anoReferencia, mesReferencia, cartaoCredito.getDiaVencimento());
        this.valor = BigDecimal.ZERO;
        this.situacao = FaturaSituacao.ABERTA;
    }

    public void updateValor(BigDecimal valorAdd) {
        this.valor = this.valor.add(valorAdd);
    }

    public void updateValor(Transacao transacao) {
        if (transacao.getTipo().equals(TransacaoTipo.COMPRA)) {
            updateValor(transacao.getValor());
        } else {
            updateValor(transacao.getValor().negate());
        }
    }

    public void fechar() {
        updateSituacao(FaturaSituacao.A_PAGAR);
    }

    public void pagar(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
        updateSituacao(FaturaSituacao.PAGA);
    }

    private void updateSituacao(FaturaSituacao situacao) {
        this.situacao = situacao;
    }


}
