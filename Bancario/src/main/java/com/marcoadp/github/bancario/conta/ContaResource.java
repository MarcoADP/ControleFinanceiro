package com.marcoadp.github.bancario.conta;

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
@RequestMapping("bancario/conta")
@RequiredArgsConstructor
@Slf4j
public class ContaResource {

    private final ContaService contaService;

    @GetMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public void status() {
        log.info(String.format("Verificando status às %s", LocalDateTime.now()));
    }

    @PostMapping
    public ResponseEntity<ContaDTO> create(@RequestBody ContaParams params) {
        Conta conta = contaService.create(params);
        return ResponseEntity.ok(ContaDTO.fromConta(conta));
    }

    @PutMapping(params = "id")
    public ResponseEntity<ContaDTO> update(@RequestParam("id") Long id, @RequestBody ContaParams params) {
        Conta conta = contaService.update(id, params);
        return ResponseEntity.ok(ContaDTO.fromConta(conta));
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<ContaDTO>> findByUsuarioCpf(@RequestParam("cpf") String cpf) {
        List<Conta> contas = contaService.findByUsuarioCpf(cpf);
        return ResponseEntity.ok(ContaDTO.fromContas(contas));
    }

    @DeleteMapping(params = "id")
    public ResponseEntity remove(@RequestParam("id") Long id) {
        contaService.deleteById(id);
        return ResponseEntity.ok().build();
    }



}
