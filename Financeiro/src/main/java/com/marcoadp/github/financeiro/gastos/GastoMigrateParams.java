package com.marcoadp.github.financeiro.gastos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GastoMigrateParams {

    @NotBlank
    private String contaNome;

    @NotBlank
    private String filename;

    @NotNull
    private Long categoriaId;

    @NotNull
    private FormaPagamento formaPagamento;

    private Long contaId;

    private Long cartaoCreditoId;



}
