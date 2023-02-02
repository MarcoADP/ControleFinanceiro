package com.marcoadp.github.bancario.conta;

import jakarta.persistence.Entity;
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
public class Conta {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String usuarioCpf;

    @NotNull
    private ContaTipo tipo;

    @NotBlank
    private String banco;

    @NotBlank
    private String descricao;

    public Conta(String usuarioCpf, ContaTipo tipo, String banco, String descricao) {
        this.usuarioCpf = usuarioCpf;
        this.tipo = tipo;
        this.banco = banco;
        this.descricao = descricao;
    }

    public void update(String usuarioCpf, ContaTipo tipo, String banco, String descricao) {
        this.usuarioCpf = usuarioCpf;
        this.tipo = tipo;
        this.banco = banco;
        this.descricao = descricao;
    }
}
