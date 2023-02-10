package com.marcoadp.github.financeiro.categoria;

import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public Categoria findByIdOrThrow(Long id) {
        return categoriaRepository.findById(id).orElseThrow();
    }

    public Categoria create(CategoriaParams params) {
        Categoria categoria = new Categoria(params.getTipo(), params.getNome(), params.getDescricao());
        return save(categoria);
    }

    public Categoria update(Long id, CategoriaParams params) {
        Categoria categoria = findByIdOrThrow(id);
        categoria.update(params.getTipo(), params.getNome(), params.getDescricao());
        return save(categoria);
    }

    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public List<Categoria> findByTipo(CategoriaTipo tipo) {
        return categoriaRepository.findByTipo(tipo);
    }

    public Categoria findByNomeOrThrow(String nome) {
        return categoriaRepository.findFirstByNome(nome).orElseThrow();
    }

    public void deleteById(Long id) {
        categoriaRepository.deleteById(id);
    }
}
