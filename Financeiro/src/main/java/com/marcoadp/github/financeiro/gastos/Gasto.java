package com.marcoadp.github.financeiro.gastos;

import com.marcoadp.github.financeiro.categoria.Categoria;
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
public class Gasto {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDate data;

    @NotBlank
    private String descricao;

    @NotNull
    private BigDecimal valor;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @NotNull
    private FormaPagamento formaPagamento;

    private Long contaId;

    private Long cartaoCreditoId;

    public Gasto(
            LocalDate data,
            String descricao,
            BigDecimal valor,
            Categoria categoria,
            FormaPagamento formaPagamento,
            Long contaId,
            Long cartaoCreditoId
    ) {
        this.data = data;
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
        this.formaPagamento = formaPagamento;
        this.contaId = contaId;
        this.cartaoCreditoId = cartaoCreditoId;
    }

    public void update(
            LocalDate data,
            String descricao,
            BigDecimal valor,
            Categoria categoria,
            FormaPagamento formaPagamento,
            Long contaId,
            Long cartaoCreditoId
    ) {
        this.data = data;
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
        this.formaPagamento = formaPagamento;
        this.contaId = contaId;
        this.cartaoCreditoId = cartaoCreditoId;
    }
}
