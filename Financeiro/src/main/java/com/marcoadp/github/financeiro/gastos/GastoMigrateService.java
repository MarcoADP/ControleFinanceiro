package com.marcoadp.github.financeiro.gastos;

import com.marcoadp.github.financeiro.categoria.Categoria;
import com.marcoadp.github.financeiro.categoria.CategoriaService;
import com.marcoadp.github.financeiro.utils.DateUtils;
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
public class GastoMigrateService {

    private final GastoService gastoService;
    private final CategoriaService categoriaService;

    public List<Gasto> migrate(GastoMigrateParams params) throws IOException {
        String csv = readCsv(params.getFilename());
        List<String> rows = separateRows(csv);
        List<GastoCsv> gastosCsv = convertRowsToGasto(rows, params.getContaNome());

        List<Gasto> gastos = gastosCsv.stream().map(gastoCsv -> createFromGastoCsv(gastoCsv, params)).toList();
        return gastos.stream().map(gastoService::save).toList();

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

    public List<GastoCsv> convertRowsToGasto(List<String> rows, String contaNome) {
        List<GastoCsv> gastos = new ArrayList<>();
        rows.forEach(row -> convertToGasto(row, contaNome).ifPresent(gastos::add));
        return gastos;
    }

    public Optional<GastoCsv> convertToGasto(String row, String contaNome) {
        List<String> columns = getColumns(row);
        if (columns.get(1).trim().equals(contaNome) && columns.get(4).trim().equals("PAGAR")) {
            return Optional.of(new GastoCsv(columns));
        }
        return Optional.empty();
    }

    private List<String> getColumns(String row) {
        return Arrays.asList(row.split(","));
    }

    private Gasto createFromGastoCsv(GastoCsv gastoCsv, GastoMigrateParams params) {
        LocalDate data = DateUtils.BrazilFormatToLocalDate(gastoCsv.getData());
        BigDecimal valor = new BigDecimal(gastoCsv.getValor());
        Categoria categoria = categoriaService.findByIdOrThrow(params.getCategoriaId());
        return new Gasto(
                data,
                gastoCsv.getDescricao(),
                valor,
                categoria,
                params.getFormaPagamento(),
                params.getContaId(),
                params.getCartaoCreditoId()
        );
    }
}
