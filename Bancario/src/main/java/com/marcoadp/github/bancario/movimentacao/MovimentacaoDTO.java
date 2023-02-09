package com.marcoadp.github.bancario.movimentacao;

import com.marcoadp.github.bancario.conta.ContaDTO;
import com.marcoadp.github.bancario.utils.DateUtils;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MovimentacaoDTO {

    private Long id;
    private ContaDTO conta;
    private String data;
    private String descricao;
    private BigDecimal valor;
    private MovimentacaoTipo tipo;
    private String dataCadastro;

    public static MovimentacaoDTO fromEntity(Movimentacao movimentacao) {
        return new MovimentacaoDTO(
                movimentacao.getId(),
                ContaDTO.fromEntity(movimentacao.getConta()),
                DateUtils.LocalDateToBrazilFormat(movimentacao.getData()),
                movimentacao.getDescricao(),
                movimentacao.getValor(),
                movimentacao.getTipo(),
                movimentacao.getDataCadastro() == null
                        ? null
                        : DateUtils.LocalDateTimeToBrazilFormat(movimentacao.getDataCadastro())
        );
    }

    public static List<MovimentacaoDTO> fromEntities(List<Movimentacao> movimentacoes) {
        return movimentacoes.stream().map(MovimentacaoDTO::fromEntity).toList();
    }


}
