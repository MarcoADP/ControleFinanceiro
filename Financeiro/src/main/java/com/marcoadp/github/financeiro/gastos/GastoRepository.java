package com.marcoadp.github.financeiro.gastos;

import com.marcoadp.github.financeiro.categoria.Categoria;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GastoRepository extends JpaRepository<Gasto, Long> {

    List<Gasto> findByCategoria(Categoria categoria);

    List<Gasto> findByCartaoCreditoId(Long cartaoCreditoId);

    List<Gasto> findByContaId(Long contaId);

}
