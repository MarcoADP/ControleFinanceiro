package com.marcoadp.github.bancario.fatura;

import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bancario/fatura")
@RequiredArgsConstructor
@Slf4j
public class FaturaResource {

    private final FaturaService faturaService;

    @GetMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public void status() {
        log.info(String.format("Verificando status às %s", LocalDateTime.now()));
    }

    @PostMapping
    public ResponseEntity<FaturaDTO> create(@RequestBody FaturaParams params) {
        Fatura fatura = faturaService.create(params);
        return ResponseEntity.ok(FaturaDTO.fromEntity(fatura));
    }

    @GetMapping(params = "cartaoCreditoId")
    public ResponseEntity<List<FaturaDTO>> findByCartaoCreditoId(@RequestParam("cartaoCreditoId") Long cartaoCreditoId) {
        List<Fatura> faturas = faturaService.findByCartaoCredito(cartaoCreditoId);
        return ResponseEntity.ok(FaturaDTO.fromEntities(faturas));
    }

    @DeleteMapping(params = "id")
    public ResponseEntity remove(@RequestParam("id") Long id) {
        faturaService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
