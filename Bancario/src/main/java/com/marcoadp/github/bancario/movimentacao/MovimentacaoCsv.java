package com.marcoadp.github.bancario.movimentacao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MovimentacaoCsv {

    private String data;
    private String conta;
    private String descricao;
    private String valor;
    private String tipo;

    public MovimentacaoCsv(List<String> columns) {
        this.data = columns.get(0).trim();
        this.conta = columns.get(1).trim();
        this.descricao = columns.get(2).trim();
        this.valor = columns.get(3).trim();
        this.tipo = columns.get(4).trim();
    }

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
