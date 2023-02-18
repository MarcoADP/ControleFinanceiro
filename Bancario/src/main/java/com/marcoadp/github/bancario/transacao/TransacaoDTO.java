package com.marcoadp.github.bancario.transacao;

import com.marcoadp.github.bancario.fatura.FaturaDTO;
import com.marcoadp.github.bancario.utils.DateUtils;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TransacaoDTO {

    private Long id;
    private FaturaDTO fatura;
    private String data;
    private String descricao;
    private BigDecimal valor;
    private TransacaoTipo tipo;
    private String dataCadastro;

    public static TransacaoDTO fromEntity(Transacao transacao) {
        return new TransacaoDTO(
                transacao.getId(),
                FaturaDTO.fromEntity(transacao.getFatura()),
                DateUtils.LocalDateToBrazilFormat(transacao.getData()),
                transacao.getDescricao(),
                transacao.getValor(),
                transacao.getTipo(),
                transacao.getDataCadastro() == null
                        ? null
                        : DateUtils.LocalDateTimeToBrazilFormat(transacao.getDataCadastro())
        );
    }

    public static List<TransacaoDTO> fromEntities(List<Transacao> transacoes) {
        return transacoes.stream().map(TransacaoDTO::fromEntity).toList();
    }


}
