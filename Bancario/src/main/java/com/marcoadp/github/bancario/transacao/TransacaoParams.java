package com.marcoadp.github.bancario.transacao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TransacaoParams {

    @NotNull
    private Long faturaId;

    @NotNull
    private String data;

    @NotBlank
    private String descricao;

    @NotNull
    private BigDecimal valor;

    @NotNull
    private TransacaoTipo tipo;

    @NotNull
    private LocalDateTime dataCadastro;


}
