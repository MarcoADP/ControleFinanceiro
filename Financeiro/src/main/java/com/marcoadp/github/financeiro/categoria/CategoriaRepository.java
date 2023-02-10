package com.marcoadp.github.financeiro.categoria;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    List<Categoria> findByTipo(CategoriaTipo tipo);

    Optional<Categoria> findFirstByNome(String nome);

}
