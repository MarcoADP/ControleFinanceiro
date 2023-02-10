package com.marcoadp.github.financeiro.categoria;

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
@RequestMapping("financeiro/categoria")
@RequiredArgsConstructor
@Slf4j
public class CategoriaResource {

    private final CategoriaService categoriaService;

    @GetMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public void status() {
        log.info(String.format("Verificando status às %s", LocalDateTime.now()));
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> create(@RequestBody CategoriaParams params) {
        Categoria categoria = categoriaService.create(params);
        return ResponseEntity.ok(CategoriaDTO.fromEntity(categoria));
    }

    @PutMapping(params = "id")
    public ResponseEntity<CategoriaDTO> update(@RequestParam("id") Long id, @RequestBody CategoriaParams params) {
        Categoria categoria = categoriaService.update(id, params);
        return ResponseEntity.ok(CategoriaDTO.fromEntity(categoria));
    }

    @GetMapping(params = "tipo")
    public ResponseEntity<List<CategoriaDTO>> findByTipo(@RequestParam("tipo") CategoriaTipo tipo) {
        List<Categoria> categorias = categoriaService.findByTipo(tipo);
        return ResponseEntity.ok(CategoriaDTO.fromEntities(categorias));
    }

    @GetMapping(params = "nome")
    public ResponseEntity<CategoriaDTO> findByNome(@RequestParam("nome") String nome) {
        Categoria categoria = categoriaService.findByNomeOrThrow(nome);
        return ResponseEntity.ok(CategoriaDTO.fromEntity(categoria));
    }

    @DeleteMapping(params = "id")
    public ResponseEntity remove(@RequestParam("id") Long id) {
        categoriaService.deleteById(id);
        return ResponseEntity.ok().build();
    }



}
