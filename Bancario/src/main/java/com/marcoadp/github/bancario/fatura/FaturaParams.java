package com.marcoadp.github.bancario.fatura;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FaturaParams {

    @NotNull
    private Long cartaoCreditoId;

    @NotNull
    private Integer mesReferencia;

    @NotNull
    private Integer anoReferencia;

}
