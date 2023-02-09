package com.marcoadp.github.bancario.movimentacao;

import com.marcoadp.github.bancario.conta.Conta;
import com.marcoadp.github.bancario.conta.ContaService;
import com.marcoadp.github.bancario.utils.DateUtils;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class MovimentacaoService {

    private final MovimentacaoRepository movimentacaoRepository;

    private final ContaService contaService;

    public Movimentacao findByIdOrThrow(Long id) {
        return movimentacaoRepository.findById(id).orElseThrow();
    }

    public Movimentacao create(MovimentacaoParams params) {
        Conta conta = contaService.findByIdOrThrow(params.getContaId());
        LocalDate data = DateUtils.BrazilFormatToLocalDate(params.getData());
        Movimentacao movimentacao = new Movimentacao(
                conta,
                data,
                params.getDescricao(),
                params.getValor(),
                params.getTipo()
        );
        return save(movimentacao);
    }

    public Movimentacao update(Long id, MovimentacaoParams params) {
        Movimentacao movimentacao = findByIdOrThrow(id);
        Conta conta = contaService.findByIdOrThrow(params.getContaId());
        LocalDate data = DateUtils.BrazilFormatToLocalDate(params.getData());
        movimentacao.update(conta, data, params.getDescricao(), params.getValor(), params.getTipo());
        return save(movimentacao);
    }

    public Movimentacao save(Movimentacao movimentacao) {
        return movimentacaoRepository.save(movimentacao);
    }

    public List<Movimentacao> findByConta(Long contaId) {
        return movimentacaoRepository.findByConta_Id(contaId);
    }

    public List<Movimentacao> findByContaPeriodo(Long contaId, LocalDate dataDe, LocalDate dateAte) {
        Conta conta = contaService.findByIdOrThrow(contaId);
        return movimentacaoRepository.findByContaAndDataBetween(conta, dataDe, dateAte);
    }

    public void deleteById(Long id) {
        movimentacaoRepository.deleteById(id);
    }
}
