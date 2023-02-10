package com.marcoadp.github.financeiro.gastos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GastoParams {

    @NotNull
    private String data;

    @NotBlank
    private String descricao;

    @NotNull
    private BigDecimal valor;

    @NotNull
    private Long categoriaId;

    @NotNull
    private FormaPagamento formaPagamento;

    private Long contaId;

    private Long cartaoCreditoId;

}
