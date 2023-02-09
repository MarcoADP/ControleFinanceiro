package com.marcoadp.github.bancario.movimentacao;

import com.marcoadp.github.bancario.consolidado.Consolidado;
import com.marcoadp.github.bancario.consolidado.ConsolidadoService;
import com.marcoadp.github.bancario.conta.Conta;
import com.marcoadp.github.bancario.conta.ContaService;
import com.marcoadp.github.bancario.utils.DateUtils;
import jakarta.transaction.Transactional;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class MovimentacaoMigrateService {

    private final MovimentacaoService movimentacaoService;
    private final ContaService contaService;
    private final ConsolidadoService consolidadoService;

    public Consolidado migrate(MovimentacaoMigrateParams params) throws IOException {
        Conta conta = contaService.findByIdOrThrow(params.getContaId());
        String csv = readCsv(params.getFilename());
        List<String> rows = separateRows(csv);
        List<MovimentacaoCsv> movimentacoesCsv = convertRowsToMovimentacao(rows, params.getContaNome());

        List<Movimentacao> movimentacoes = movimentacoesCsv.stream()
                .map(movCsv -> createFromMovimentacaoCsv(movCsv, conta)).toList();
        movimentacoes.forEach(movimentacaoService::save);

        return consolidadoService.create(conta, movimentacoes);

    }

    public String readCsv(String filename) throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try (InputStream inputStream =  classloader.getResourceAsStream(filename)) {

            if (inputStream == null) {
                throw new FileNotFoundException("Sem filmes");
            }

            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

        } catch (IOException e) {
            throw new IOException("Arquivo não encontrado. Verifique se o CSV está na pasta Resources!");
        }
    }

    public List<String> separateRows(String csv) {
        return Arrays.asList(csv.split("\n"));
    }

    public List<MovimentacaoCsv> convertRowsToMovimentacao(List<String> rows, String contaNome) {
        List<MovimentacaoCsv> movimentacoes = new ArrayList<>();
        rows.forEach(row -> convertToMovie(row, contaNome).ifPresent(movimentacoes::add));
        return movimentacoes;
    }

    public Optional<MovimentacaoCsv> convertToMovie(String row, String contaNome) {
        List<String> columns = getColumns(row);
        if (columns.get(1).trim().equals(contaNome)) {
            return Optional.of(new MovimentacaoCsv(columns));
        }
        return Optional.empty();
    }

    private List<String> getColumns(String row) {
        return Arrays.asList(row.split(","));
    }

    private Movimentacao createFromMovimentacaoCsv(MovimentacaoCsv movimentacaoCsv, Conta conta) {
        LocalDate data = DateUtils.BrazilFormatToLocalDate(movimentacaoCsv.getData());
        BigDecimal valor = new BigDecimal(movimentacaoCsv.getValor());
        MovimentacaoTipo tipo = movimentacaoCsv.getTipo().equals("PAGAR")
                ? MovimentacaoTipo.SAIDA
                : MovimentacaoTipo.ENTRADA;
        return new Movimentacao(
                conta,
                data,
                movimentacaoCsv.getDescricao(),
                valor,
                tipo
        );
    }
}
