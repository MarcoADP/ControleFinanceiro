package com.marcoadp.github.bancario.consolidado;

import com.marcoadp.github.bancario.conta.ContaDTO;
import com.marcoadp.github.bancario.utils.DateUtils;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ConsolidadoDTO {

    private Long id;
    private ContaDTO conta;
    private String data;
    private BigDecimal valor;

    public static ConsolidadoDTO fromEntity(Consolidado consolidado) {
        return new ConsolidadoDTO(
                consolidado.getId(),
                ContaDTO.fromEntity(consolidado.getConta()),
                DateUtils.LocalDateTimeToBrazilFormat(consolidado.getData()),
                consolidado.getValor()
        );
    }

    public static List<ConsolidadoDTO> fromEntities(List<Consolidado> consolidados) {
        return consolidados.stream().map(ConsolidadoDTO::fromEntity).toList();
    }

}
