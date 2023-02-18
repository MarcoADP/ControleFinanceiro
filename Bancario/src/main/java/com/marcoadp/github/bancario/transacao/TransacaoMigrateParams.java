package com.marcoadp.github.bancario.transacao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TransacaoMigrateParams {

    @NotNull
    private Long cartaoCreditoId;

    @NotBlank
    private String cartaoNome;

    @NotBlank
    private String filename;



}
