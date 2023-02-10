package com.marcoadp.github.financeiro.gastos;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("financeiro/gasto")
@RequiredArgsConstructor
@Slf4j
public class GastoResource {

    private final GastoService gastoService;
    private final GastoMigrateService gastoMigrateService;

    @GetMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public void status() {
        log.info(String.format("Verificando status às %s", LocalDateTime.now()));
    }

    @PostMapping
    public ResponseEntity<GastoDTO> create(@RequestBody GastoParams params) {
        Gasto gasto = gastoService.create(params);
        return ResponseEntity.ok(GastoDTO.fromEntity(gasto));
    }

    @PutMapping(params = "id")
    public ResponseEntity<GastoDTO> update(@RequestParam("id") Long id, @RequestBody GastoParams params) {
        Gasto gasto = gastoService.update(id, params);
        return ResponseEntity.ok(GastoDTO.fromEntity(gasto));
    }

    @GetMapping(params = "categoriaId")
    public ResponseEntity<List<GastoDTO>> findByCategoria(@RequestParam("categoriaId") Long categoriaId) {
        List<Gasto> gastos = gastoService.findByCategoria(categoriaId);
        return ResponseEntity.ok(GastoDTO.fromEntities(gastos));
    }

    @GetMapping(params = "cartaoCreditoId")
    public ResponseEntity<List<GastoDTO>> findByCartaoCreditoId(@RequestParam("cartaoCreditoId") Long cartaoCreditoId) {
        List<Gasto> gastos = gastoService.findByCartaoCreditoId(cartaoCreditoId);
        return ResponseEntity.ok(GastoDTO.fromEntities(gastos));
    }

    @GetMapping(params = "contaId")
    public ResponseEntity<List<GastoDTO>> findByContaId(@RequestParam("contaId") Long contaId) {
        List<Gasto> gastos = gastoService.findByContaId(contaId);
        return ResponseEntity.ok(GastoDTO.fromEntities(gastos));
    }

    @DeleteMapping(params = "id")
    public ResponseEntity remove(@RequestParam("id") Long id) {
        gastoService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "migrar")
    public ResponseEntity<List<GastoDTO>> migrate(@RequestBody GastoMigrateParams params) throws IOException {
        List<Gasto> gastos = gastoMigrateService.migrate(params);
        return ResponseEntity.ok(GastoDTO.fromEntities(gastos));
    }

}
