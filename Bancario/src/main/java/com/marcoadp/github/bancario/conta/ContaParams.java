package com.marcoadp.github.bancario.conta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ContaParams {

    @NotNull
    private String usuarioCpf;

    @NotNull
    private ContaTipo tipo;

    @NotBlank
    private String banco;

    @NotBlank
    private String descricao;
}
