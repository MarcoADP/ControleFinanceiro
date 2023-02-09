package com.marcoadp.github.bancario.cartaocredito;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartaoCreditoParams {

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

}
