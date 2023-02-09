package com.marcoadp.github.bancario.fatura;

import com.marcoadp.github.bancario.cartaocredito.CartaoCreditoDTO;
import com.marcoadp.github.bancario.movimentacao.MovimentacaoDTO;
import com.marcoadp.github.bancario.utils.DateUtils;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FaturaDTO {

    private Long id;
    private CartaoCreditoDTO cartaoCredito;
    private String descricao;
    private FaturaSituacao situacao;
    private String dataVencimento;
    private String dataPagamento;
    private BigDecimal valor;
    private MovimentacaoDTO movimentacao;

    public static FaturaDTO fromEntity(Fatura fatura) {
        return new FaturaDTO(
                fatura.getId(),
                CartaoCreditoDTO.fromEntity(fatura.getCartaoCredito()),
                fatura.getDescricao(),
                fatura.getSituacao(),
                DateUtils.LocalDateToBrazilFormat(fatura.getDataVencimento()),
                DateUtils.LocalDateToBrazilFormat(fatura.getDataPagamento()),
                fatura.getValor(),
                MovimentacaoDTO.fromEntity(fatura.getMovimentacao())
        );
    }
}
