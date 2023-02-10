package com.marcoadp.github.financeiro.gastos;

import com.marcoadp.github.financeiro.categoria.CategoriaDTO;
import com.marcoadp.github.financeiro.utils.DateUtils;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GastoDTO {

    private Long id;
    private String data;
    private String descricao;
    private BigDecimal valor;
    private CategoriaDTO categoria;
    private FormaPagamento formaPagamento;
    private Long contaId;
    private Long cartaoCreditoId;

    public static GastoDTO fromEntity(Gasto gasto) {
        return new GastoDTO(
                gasto.getId(),
                DateUtils.LocalDateToBrazilFormat(gasto.getData()),
                gasto.getDescricao(),
                gasto.getValor(),
                CategoriaDTO.fromEntity(gasto.getCategoria()),
                gasto.getFormaPagamento(),
                gasto.getContaId(),
                gasto.getCartaoCreditoId()
        );
    }

    public static List<GastoDTO> fromEntities(List<Gasto> gastos) {
        return gastos.stream().map(GastoDTO::fromEntity).toList();
    }

}
