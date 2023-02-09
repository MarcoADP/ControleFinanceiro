package com.marcoadp.github.bancario.consolidado;

import com.marcoadp.github.bancario.conta.Conta;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Consolidado {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "conta_id")
    private Conta conta;

    @NotNull
    private LocalDateTime data;

    @NotNull
    private BigDecimal valor;

    public Consolidado(Conta conta, BigDecimal valor) {
        this.conta = conta;
        this.data = LocalDateTime.now();
        this.valor = valor;
    }
}
