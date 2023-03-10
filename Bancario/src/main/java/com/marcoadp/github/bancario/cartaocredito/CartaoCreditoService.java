package com.marcoadp.github.bancario.cartaocredito;

import com.marcoadp.github.bancario.fatura.Fatura;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class CartaoCreditoService {

    private final CartaoCreditoRepository cartaoCreditoRepository;

    public CartaoCredito findByIdOrThrow(Long id) {
        return cartaoCreditoRepository.findById(id).orElseThrow();
    }

    public CartaoCredito create(CartaoCreditoParams params) {
        CartaoCredito cartaoCredito = new CartaoCredito(
                params.getUsuarioCpf(),
                params.getBanco(),
                params.getDescricao(),
                params.getDiaFechamento(),
                params.getDiaVencimento(),
                params.getLimite()
        );
        return save(cartaoCredito);
    }

    public CartaoCredito update(Long id, CartaoCreditoParams params) {
        CartaoCredito cartaoCredito = findByIdOrThrow(id);
        cartaoCredito.update(
                params.getUsuarioCpf(),
                params.getBanco(),
                params.getDescricao(),
                params.getDiaFechamento(),
                params.getDiaVencimento(),
                params.getLimite()
        );
        return save(cartaoCredito);
    }

    public void updateFaturaParcial(Long id, BigDecimal valor) {
        //TODO ACHAR FATURA ATUAL DO CARTAO E ADD O VALOR
        //TODO PASSAR PARA FATURA SERVICE?
        Fatura fatura = new Fatura();
        fatura.updateValor(valor);
    }

    public CartaoCredito save(CartaoCredito cartaoCredito) {
        return cartaoCreditoRepository.save(cartaoCredito);
    }

    public List<CartaoCredito> findByUsuarioCpf(String cpf) {
        return cartaoCreditoRepository.findByUsuarioCpf(cpf);
    }

    public void deleteById(Long id) {
        cartaoCreditoRepository.deleteById(id);
    }
}
