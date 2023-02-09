package com.marcoadp.github.bancario.cartaocredito;

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
@RequestMapping("bancario/cartao-credito")
@RequiredArgsConstructor
@Slf4j
public class CartaoCreditoResource {

    private final CartaoCreditoService cartaoCreditoService;

    @GetMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public void status() {
        log.info(String.format("Verificando status às %s", LocalDateTime.now()));
    }

    @PostMapping
    public ResponseEntity<CartaoCreditoDTO> create(@RequestBody CartaoCreditoParams params) {
        CartaoCredito cartaoCredito = cartaoCreditoService.create(params);
        return ResponseEntity.ok(CartaoCreditoDTO.fromEntity(cartaoCredito));
    }

    @PutMapping(params = "id")
    public ResponseEntity<CartaoCreditoDTO> update(@RequestParam("id") Long id, @RequestBody CartaoCreditoParams params) {
        CartaoCredito cartaoCredito = cartaoCreditoService.update(id, params);
        return ResponseEntity.ok(CartaoCreditoDTO.fromEntity(cartaoCredito));
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CartaoCreditoDTO>> findByUsuarioCpf(@RequestParam("cpf") String cpf) {
        List<CartaoCredito> cartaoCreditos = cartaoCreditoService.findByUsuarioCpf(cpf);
        return ResponseEntity.ok(CartaoCreditoDTO.fromEntities(cartaoCreditos));
    }

    @DeleteMapping(params = "id")
    public ResponseEntity remove(@RequestParam("id") Long id) {
        cartaoCreditoService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "fatura-parcial", params = "id")
    public ResponseEntity<CartaoCreditoDTO> updateFaturaParcial(
            @RequestParam("id") Long id, @RequestBody CartaoCreditoFaturaParcialParams params
    ) {
        CartaoCredito cartaoCredito = cartaoCreditoService.updateFaturaParcial(id, params.getValor());
        return ResponseEntity.ok(CartaoCreditoDTO.fromEntity(cartaoCredito));
    }

}
