package com.marcoadp.github.bancario.movimentacao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MovimentacaoMigrateParams {

    @NotNull
    private Long contaId;

    @NotBlank
    private String contaNome;

    @NotBlank
    private String filename;



}
