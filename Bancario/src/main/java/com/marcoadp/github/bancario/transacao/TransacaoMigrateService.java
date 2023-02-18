package com.marcoadp.github.bancario.transacao;

import com.marcoadp.github.bancario.cartaocredito.CartaoCredito;
import com.marcoadp.github.bancario.cartaocredito.CartaoCreditoService;
import com.marcoadp.github.bancario.fatura.Fatura;
import com.marcoadp.github.bancario.fatura.FaturaService;
import com.marcoadp.github.bancario.utils.DateUtils;
import jakarta.transaction.Transactional;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class TransacaoMigrateService {

    private final TransacaoService transcaoService;
    private final CartaoCreditoService cartaoCreditoService;
    private final FaturaService faturaService;

    // TODO AGRUPAR METODOS DE OPERACAO NO CSV EM UMA UNICA CLASSE

    public List<Fatura> migrate(TransacaoMigrateParams params) throws IOException {

        CartaoCredito cartaoCredito = cartaoCreditoService.findByIdOrThrow(params.getCartaoCreditoId());

        String csv = readCsv(params.getFilename());
        List<String> rows = separateRows(csv);
        List<TransacaoCsv> transacoesCsv = convertRowsToMovimentacao(rows, params.getCartaoNome());

        Map<YearMonth, List<TransacaoCsv>> transacoesByMonth = transacoesCsv.stream()
                .collect(Collectors.groupingBy(TransacaoCsv::getYearMonth));

        List<Fatura> faturas = new ArrayList<>();
        for (YearMonth yearMonth: transacoesByMonth.keySet()) {
            Fatura fatura = faturaService.create(cartaoCredito, yearMonth.getMonthValue(), yearMonth.getYear());
            List<Transacao> transacoes = transacoesByMonth.get(yearMonth).stream()
                    .map(transacaoCsv -> createFromTransacaoCsv(transacaoCsv, fatura)).toList();

            transacoes.forEach(transcaoService::save);
            transacoes.forEach(fatura::updateValor);
            faturas.add(fatura);

        }

        return faturas;


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

    public List<TransacaoCsv> convertRowsToMovimentacao(List<String> rows, String cartaoNome) {
        List<TransacaoCsv> transacoes = new ArrayList<>();
        rows.forEach(row -> convertToTransacao(row, cartaoNome).ifPresent(transacoes::add));
        return transacoes;
    }

    public Optional<TransacaoCsv> convertToTransacao(String row, String cartaoNome) {
        List<String> columns = getColumns(row);
        if (columns.get(1).trim().equals(cartaoNome)) {
            return Optional.of(new TransacaoCsv(columns));
        }
        return Optional.empty();
    }

    private List<String> getColumns(String row) {
        return Arrays.asList(row.split(","));
    }

    private Transacao createFromTransacaoCsv(TransacaoCsv transacaoCsv, Fatura fatura) {
        LocalDate data = DateUtils.BrazilFormatToLocalDate(transacaoCsv.getData());
        BigDecimal valor = new BigDecimal(transacaoCsv.getValor());
        TransacaoTipo tipo = transacaoCsv.getTipo().equals("PAGAR")
                ? TransacaoTipo.COMPRA
                : TransacaoTipo.REEMBOLSO;
        return new Transacao(
                fatura,
                data,
                transacaoCsv.getDescricao(),
                valor,
                tipo
        );
    }
}
