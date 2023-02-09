package com.marcoadp.github.bancario.cartaocredito;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CartaoCreditoDTO {

    private Long id;
    private String usuarioCpf;
    private String banco;
    private String descricao;
    private Integer diaFechamento;
    private Integer diaVencimento;
    private BigDecimal limite;
    private BigDecimal faturaParcial;

    public static CartaoCreditoDTO fromEntity(CartaoCredito cartaoCredito) {
        return new CartaoCreditoDTO(
                cartaoCredito.getId(),
                cartaoCredito.getUsuarioCpf(),
                cartaoCredito.getBanco(),
                cartaoCredito.getDescricao(),
                cartaoCredito.getDiaFechamento(),
                cartaoCredito.getDiaVencimento(),
                cartaoCredito.getLimite(),
                cartaoCredito.getFaturaParcial()
        );
    }

    public static List<CartaoCreditoDTO> fromEntities(List<CartaoCredito> cartoes) {
        return cartoes.stream().map(CartaoCreditoDTO::fromEntity).toList();
    }

}
