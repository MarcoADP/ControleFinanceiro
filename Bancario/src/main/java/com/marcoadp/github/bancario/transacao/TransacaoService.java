package com.marcoadp.github.bancario.transacao;

import com.marcoadp.github.bancario.fatura.Fatura;
import com.marcoadp.github.bancario.fatura.FaturaService;
import com.marcoadp.github.bancario.utils.DateUtils;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;

    private final FaturaService faturaService;

    public Transacao findByIdOrThrow(Long id) {
        return transacaoRepository.findById(id).orElseThrow();
    }

    public Transacao create(TransacaoParams params) {
        Fatura fatura = faturaService.findByIdOrThrow(params.getFaturaId());
        LocalDate data = DateUtils.BrazilFormatToLocalDate(params.getData());
        Transacao transacao = new Transacao(
                fatura,
                data,
                params.getDescricao(),
                params.getValor(),
                params.getTipo()
        );
        return save(transacao);
    }

    public Transacao update(Long id, TransacaoParams params) {
        Transacao transacao = findByIdOrThrow(id);
        Fatura fatura = faturaService.findByIdOrThrow(params.getFaturaId());
        LocalDate data = DateUtils.BrazilFormatToLocalDate(params.getData());
        transacao.update(fatura, data, params.getDescricao(), params.getValor(), params.getTipo());
        return save(transacao);
    }

    public Transacao save(Transacao transacao) {
        return transacaoRepository.save(transacao);
    }

    public List<Transacao> findByFatura(Long faturaId) {
        Fatura fatura = faturaService.findByIdOrThrow(faturaId);
        return transacaoRepository.findByFatura(fatura);
    }

    public List<Transacao> findByFaturaPeriodo(Long faturaId, LocalDate dataDe, LocalDate dateAte) {
        Fatura fatura = faturaService.findByIdOrThrow(faturaId);
        return transacaoRepository.findByFaturaAndDataBetween(fatura, dataDe, dateAte);
    }

    public void deleteById(Long id) {
        transacaoRepository.deleteById(id);
    }
}
