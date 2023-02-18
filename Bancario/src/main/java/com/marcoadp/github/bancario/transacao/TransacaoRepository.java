package com.marcoadp.github.bancario.transacao;

import com.marcoadp.github.bancario.fatura.Fatura;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    List<Transacao> findByFatura(Fatura fatura);

    List<Transacao> findByFaturaAndDataBetween(Fatura fatura, LocalDate dataDe, LocalDate dataAte);

}
