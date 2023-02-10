package com.marcoadp.github.financeiro.categoria;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategoriaDTO {

    private Long id;
    private CategoriaTipo tipo;
    private String nome;
    private String descricao;

    public static CategoriaDTO fromEntity(Categoria categoria) {
        return new CategoriaDTO(categoria.getId(), categoria.getTipo(), categoria.getNome(), categoria.getDescricao());
    }

    public static List<CategoriaDTO> fromEntities(List<Categoria> categorias) {
        return categorias.stream().map(CategoriaDTO::fromEntity).toList();
    }

}
