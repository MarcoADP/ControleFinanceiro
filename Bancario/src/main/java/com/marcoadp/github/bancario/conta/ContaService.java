package com.marcoadp.github.bancario.conta;

import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ContaService {

    private final ContaRepository contaRepository;

    public Conta findByIdOrThrow(Long id) {
        return contaRepository.findById(id).orElseThrow();
    }

    public Conta create(ContaParams params) {
        Conta conta = new Conta(params.getUsuarioCpf(), params.getTipo(), params.getBanco(), params.getDescricao());
        return save(conta);
    }

    public Conta update(Long id, ContaParams params) {
        Conta conta = findByIdOrThrow(id);
        conta.update(params.getUsuarioCpf(), params.getTipo(), params.getBanco(), params.getDescricao());
        return save(conta);
    }

    public Conta save(Conta conta) {
        return contaRepository.save(conta);
    }

    public List<Conta> findByUsuarioCpf(String cpf) {
        return contaRepository.findByUsuarioCpf(cpf);
    }

    public void deleteById(Long id) {
        contaRepository.deleteById(id);
    }
}
