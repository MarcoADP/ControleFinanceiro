package com.marcoadp.github.bancario.consolidado;

import com.marcoadp.github.bancario.conta.Conta;
import com.marcoadp.github.bancario.movimentacao.Movimentacao;
import com.marcoadp.github.bancario.movimentacao.MovimentacaoTipo;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ConsolidadoService {

    private final ConsolidadoRepository consolidadoRepository;

    public Consolidado findByIdOrThrow(Long id) {
        return consolidadoRepository.findById(id).orElseThrow();
    }

    public Consolidado create(Conta conta, BigDecimal valor) {
        BigDecimal valorAntigo = findValorAntigo(conta);
        BigDecimal valorAtualizado = valorAntigo.add(valor);
        Consolidado consolidado = new Consolidado(conta, valorAtualizado);
        return save(consolidado);
    }

    public Consolidado create(Conta conta, List<Movimentacao> movimentacoes) {
        BigDecimal valor = BigDecimal.ZERO;
        for (Movimentacao movimentacao : movimentacoes) {
            if (MovimentacaoTipo.ENTRADA.equals(movimentacao.getTipo())) {
                valor = valor.add(movimentacao.getValor());
            } else {
                valor = valor.subtract(movimentacao.getValor());
            }
        }
        return create(conta, valor);
    }

    public Consolidado save(Consolidado consolidado) {
        return consolidadoRepository.save(consolidado);
    }

    public List<Consolidado> findByConta(Long contaId) {
        return consolidadoRepository.findByConta_Id(contaId);
    }

    public BigDecimal findValorAntigo(Conta conta) {
        Optional<Consolidado> consolidadoOpt = consolidadoRepository.findFirstByContaOrderByDataDesc(conta);
        return consolidadoOpt.isPresent() ? consolidadoOpt.get().getValor() : BigDecimal.ZERO;
    }

    public void deleteById(Long id) {
        consolidadoRepository.deleteById(id);
    }
}
