package com.marcoadp.github.bancario.movimentacao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcoadp.github.bancario.conta.Conta;
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
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Movimentacao {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "conta_id")
    private Conta conta;

    @NotNull
    private LocalDate data;

    @NotBlank
    private String descricao;

    @NotNull
    private BigDecimal valor;

    @NotNull
    private MovimentacaoTipo tipo;

    @NotNull
    private LocalDateTime dataCadastro;

    public Movimentacao(Conta conta, LocalDate data, String descricao, BigDecimal valor, MovimentacaoTipo tipo) {
        this.conta = conta;
        this.data = data;
        this.descricao = descricao;
        this.valor = valor;
        this.tipo = tipo;
        this.dataCadastro = LocalDateTime.now();
    }

    public void update(Conta conta, LocalDate data, String descricao, BigDecimal valor, MovimentacaoTipo tipo) {
        this.conta = conta;
        this.data = data;
        this.descricao = descricao;
        this.valor = valor;
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
