package com.marcoadp.github.financeiro.categoria;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Categoria {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CategoriaTipo tipo;

    @NotBlank
    private String nome;

    private String descricao;

    public Categoria(CategoriaTipo tipo, String nome, String descricao) {
        this.tipo = tipo;
        this.nome = nome;
        this.descricao = descricao;
    }

    public void update(CategoriaTipo tipo, String nome, String descricao) {
        this.tipo = tipo;
        this.nome = nome;
        this.descricao = descricao;
    }
}
