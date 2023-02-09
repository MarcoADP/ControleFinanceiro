package com.marcoadp.github.bancario.movimentacao;

import com.marcoadp.github.bancario.conta.Conta;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {

    List<Movimentacao> findByConta_Id(Long contaId);

    List<Movimentacao> findByContaAndDataBetween(Conta conta, LocalDate dataDe, LocalDate dataAte);

}
