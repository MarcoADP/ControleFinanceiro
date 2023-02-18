package com.marcoadp.github.bancario.fatura;

import com.marcoadp.github.bancario.cartaocredito.CartaoCredito;
import com.marcoadp.github.bancario.cartaocredito.CartaoCreditoService;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class FaturaService {

    private final FaturaRepository faturaRepository;
    private final CartaoCreditoService cartaoCreditoService;

    public Fatura findByIdOrThrow(Long id) {
        return faturaRepository.findById(id).orElseThrow();
    }

    public Fatura create(FaturaParams params) {
        CartaoCredito cartaoCredito = cartaoCreditoService.findByIdOrThrow(params.getCartaoCreditoId());
        return create(cartaoCredito, params.getMesReferencia(), params.getAnoReferencia());
    }

    public Fatura create(CartaoCredito cartaoCredito, Integer mesReferencia, Integer anoReferencia) {
        Fatura fatura = new Fatura(cartaoCredito, mesReferencia, anoReferencia);
        return save(fatura);
    }

    public Fatura save(Fatura fatura) {
        return faturaRepository.save(fatura);
    }

    public List<Fatura> findByCartaoCredito(Long cartaoCreditoId) {
        CartaoCredito cartaoCredito = cartaoCreditoService.findByIdOrThrow(cartaoCreditoId);
        return faturaRepository.findByCartaoCredito(cartaoCredito);
    }

    public Fatura findAbertaByCartaoCredito(Long cartaoCreditoId) {
        CartaoCredito cartaoCredito = cartaoCreditoService.findByIdOrThrow(cartaoCreditoId);
        return faturaRepository.findFirstByCartaoCreditoAndSituacao(cartaoCredito, FaturaSituacao.ABERTA).orElseThrow();
    }

    public List<Fatura> findAbertas() {
        return faturaRepository.findBySituacao(FaturaSituacao.ABERTA);
    }

    public void deleteById(Long id) {
        faturaRepository.deleteById(id);
    }
}
