package com.marcoadp.github.bancario.transacao;

import com.marcoadp.github.bancario.fatura.Fatura;
import com.marcoadp.github.bancario.fatura.FaturaDTO;
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
@RequestMapping("bancario/transacao")
@RequiredArgsConstructor
@Slf4j
public class TransacaoResource {

    private final TransacaoService transacaoService;
    private final TransacaoMigrateService transacaoMigrateService;

    @GetMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public void status() {
        log.info(String.format("Verificando status às %s", LocalDateTime.now()));
    }

    @PostMapping
    public ResponseEntity<TransacaoDTO> create(@RequestBody TransacaoParams params) {
        Transacao transacao = transacaoService.create(params);
        return ResponseEntity.ok(TransacaoDTO.fromEntity(transacao));
    }

    @PutMapping(params = "id")
    public ResponseEntity<TransacaoDTO> update(@RequestParam("id") Long id, @RequestBody TransacaoParams params) {
        Transacao transacao = transacaoService.update(id, params);
        return ResponseEntity.ok(TransacaoDTO.fromEntity(transacao));
    }

    @GetMapping(params = "faturaId")
    public ResponseEntity<List<TransacaoDTO>> findByConta(@RequestParam("faturaId") Long faturaId) {
        List<Transacao> transacoes = transacaoService.findByFatura(faturaId);
        return ResponseEntity.ok(TransacaoDTO.fromEntities(transacoes));
    }

    @DeleteMapping(params = "id")
    public ResponseEntity remove(@RequestParam("id") Long id) {
        transacaoService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "migrar")
    public ResponseEntity<List<FaturaDTO>> migrate(@RequestBody TransacaoMigrateParams params) throws IOException {
        List<Fatura> faturas = transacaoMigrateService.migrate(params);
        return ResponseEntity.ok(FaturaDTO.fromEntities(faturas));
    }


}
