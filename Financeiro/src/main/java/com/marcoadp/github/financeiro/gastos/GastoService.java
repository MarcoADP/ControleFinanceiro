package com.marcoadp.github.financeiro.gastos;

import com.marcoadp.github.financeiro.categoria.Categoria;
import com.marcoadp.github.financeiro.categoria.CategoriaService;
import com.marcoadp.github.financeiro.utils.DateUtils;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class GastoService {

    private final GastoRepository gastoRepository;

    private final CategoriaService categoriaService;

    public Gasto findByIdOrThrow(Long id) {
        return gastoRepository.findById(id).orElseThrow();
    }

    public Gasto create(GastoParams params) {
        Categoria categoria = categoriaService.findByIdOrThrow(params.getCategoriaId());
        Gasto gasto = new Gasto(
                DateUtils.BrazilFormatToLocalDate(params.getData()),
                params.getDescricao(),
                params.getValor(),
                categoria,
                params.getFormaPagamento(),
                params.getContaId(),
                params.getCartaoCreditoId()
        );
        return save(gasto);
    }

    public Gasto update(Long id, GastoParams params) {
        Gasto gasto = findByIdOrThrow(id);
        Categoria categoria = categoriaService.findByIdOrThrow(params.getCategoriaId());
        gasto.update(
                DateUtils.BrazilFormatToLocalDate(params.getData()),
                params.getDescricao(),
                params.getValor(),
                categoria,
                params.getFormaPagamento(),
                params.getContaId(),
                params.getCartaoCreditoId()
        );
        return save(gasto);
    }

    public Gasto save(Gasto gasto) {
        return gastoRepository.save(gasto);
    }

    public List<Gasto> findByCategoria(Long categoriaId) {
        Categoria categoria = categoriaService.findByIdOrThrow(categoriaId);
        return gastoRepository.findByCategoria(categoria);
    }

    public List<Gasto> findByCartaoCreditoId(Long cartaoCreditoId) {
        return gastoRepository.findByCartaoCreditoId(cartaoCreditoId);
    }

    public List<Gasto> findByContaId(Long contaId) {
        return gastoRepository.findByContaId(contaId);
    }

    public void deleteById(Long id) {
        gastoRepository.deleteById(id);
    }
}
