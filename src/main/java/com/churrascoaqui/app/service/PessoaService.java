package com.churrascoaqui.app.service;

import com.churrascoaqui.app.entity.Pessoa;
import com.churrascoaqui.app.entity.PessoaDTO;
import com.churrascoaqui.app.exception.NotFoundException;
import com.churrascoaqui.app.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa buscarPorId(Long id){
        return pessoaRepository.findById(id).orElseThrow(() -> new NotFoundException("Pessoa não encontrada"));
    }

    public List<Pessoa> listarTodos(){
        return pessoaRepository.findAll();
    }

    public Pessoa salvar(PessoaDTO pessoaDTO){
        Pessoa novaPessoa = new Pessoa();
        novaPessoa.setCpf(pessoaDTO.getCpf());
        novaPessoa.setNome(pessoaDTO.getNome());
        novaPessoa.setDataNascimento(pessoaDTO.getDataNascimento());
        return pessoaRepository.save(novaPessoa);
    }

    public Pessoa atualizar(Long id, PessoaDTO pessoaDTO){
        Pessoa pessoaEncontrada = pessoaRepository.findById(id).orElseThrow(() -> new NotFoundException("Pessoa não encontrada"));
        pessoaEncontrada.setCpf(pessoaDTO.getCpf());
        pessoaEncontrada.setNome(pessoaDTO.getNome());
        pessoaEncontrada.setDataNascimento(pessoaDTO.getDataNascimento());

        return pessoaRepository.save(pessoaEncontrada);
    }

    public void deletar(Long id){
        Pessoa pessoaEncontrada = pessoaRepository.findById(id).orElseThrow(() -> new NotFoundException("Pessoa não encontrada"));
        pessoaRepository.deleteById(id);
    }
}
