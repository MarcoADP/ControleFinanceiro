package com.marcoadp.github.bancario.movimentacao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MovimentacaoParams {

    @NotNull
    private Long contaId;

    @NotNull
    private String data;

    @NotBlank
    private String descricao;

    @NotNull
    private BigDecimal valor;

    @NotNull
    private MovimentacaoTipo tipo;

    @NotNull
    private LocalDateTime dataCadastro;


}
