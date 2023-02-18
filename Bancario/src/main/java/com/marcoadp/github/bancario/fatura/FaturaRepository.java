package com.marcoadp.github.bancario.fatura;

import com.marcoadp.github.bancario.cartaocredito.CartaoCredito;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaturaRepository extends JpaRepository<Fatura, Long> {

    List<Fatura> findByCartaoCredito(CartaoCredito cartaoCredito);

    Optional<Fatura> findFirstByCartaoCreditoAndSituacao(CartaoCredito cartaoCredito, FaturaSituacao situacao);

    List<Fatura> findBySituacao(FaturaSituacao situacao);

}
