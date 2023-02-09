package com.marcoadp.github.bancario.conta;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ContaDTO {

    private Long id;
    private String usuarioCpf;
    private ContaTipo tipo;
    private String banco;
    private String descricao;

    public static ContaDTO fromEntity(Conta conta) {
        return new ContaDTO(
                conta.getId(),
                conta.getUsuarioCpf(),
                conta.getTipo(),
                conta.getBanco(),
                conta.getDescricao()
        );
    }

    public static List<ContaDTO> fromEntities(List<Conta> contas) {
        return contas.stream().map(ContaDTO::fromEntity).toList();
    }

}
