package com.marcoadp.github.bancario.consolidado;

import com.marcoadp.github.bancario.conta.Conta;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsolidadoRepository extends JpaRepository<Consolidado, Long> {

    List<Consolidado> findByConta_Id(Long contaId);

    Optional<Consolidado> findFirstByContaOrderByDataDesc(Conta conta);

}
