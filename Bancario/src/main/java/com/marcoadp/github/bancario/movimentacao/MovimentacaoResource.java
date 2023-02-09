package com.marcoadp.github.bancario.movimentacao;

import com.marcoadp.github.bancario.consolidado.Consolidado;
import com.marcoadp.github.bancario.consolidado.ConsolidadoDTO;
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
@RequestMapping("bancario/movimentacao")
@RequiredArgsConstructor
@Slf4j
public class MovimentacaoResource {

    private final MovimentacaoService movimentacaoService;
    private final MovimentacaoMigrateService movimentacaoMigrateService;

    @GetMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public void status() {
        log.info(String.format("Verificando status às %s", LocalDateTime.now()));
    }

    @PostMapping
    public ResponseEntity<MovimentacaoDTO> create(@RequestBody MovimentacaoParams params) {
        Movimentacao movimentacao = movimentacaoService.create(params);
        return ResponseEntity.ok(MovimentacaoDTO.fromEntity(movimentacao));
    }

    @PutMapping(params = "id")
    public ResponseEntity<MovimentacaoDTO> update(@RequestParam("id") Long id, @RequestBody MovimentacaoParams params) {
        Movimentacao movimentacao = movimentacaoService.update(id, params);
        return ResponseEntity.ok(MovimentacaoDTO.fromEntity(movimentacao));
    }

    @GetMapping(params = "conta")
    public ResponseEntity<List<MovimentacaoDTO>> findByConta(@RequestParam("conta") Long contaId) {
        List<Movimentacao> movimentacoes = movimentacaoService.findByConta(contaId);
        return ResponseEntity.ok(MovimentacaoDTO.fromEntities(movimentacoes));
    }

    @DeleteMapping(params = "id")
    public ResponseEntity remove(@RequestParam("id") Long id) {
        movimentacaoService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "migrar")
    public ResponseEntity<ConsolidadoDTO> migrate(@RequestBody MovimentacaoMigrateParams params) throws IOException {
        Consolidado consolidado = movimentacaoMigrateService.migrate(params);
        return ResponseEntity.ok(ConsolidadoDTO.fromEntity(consolidado));
    }


}
