package com.marcoadp.github.financeiro.categoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategoriaParams {

    @NotNull
    private CategoriaTipo tipo;

    @NotBlank
    private String nome;

    private String descricao;

}
