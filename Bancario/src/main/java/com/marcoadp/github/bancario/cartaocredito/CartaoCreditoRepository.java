package com.marcoadp.github.bancario.cartaocredito;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoCreditoRepository extends JpaRepository<CartaoCredito, Long> {

    List<CartaoCredito> findByUsuarioCpf(String cpf);

}
