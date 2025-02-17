package com.churrascoaqui.app.service;

import com.churrascoaqui.app.entity.Contribuicao;
import com.churrascoaqui.app.entity.ContribuicaoDTO;
import com.churrascoaqui.app.exception.NotFoundException;
import com.churrascoaqui.app.repository.ContribuicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContribuicaoService {
    @Autowired
    private ContribuicaoRepository contribuicaoRepository;

    public Contribuicao salvar(ContribuicaoDTO contribuicaoDTO){
        Contribuicao contribuicao = new Contribuicao();
        contribuicao.setChurrasco(contribuicaoDTO.getChurrasco());
        contribuicao.setItem(contribuicaoDTO.getItem());
        contribuicao.setPessoa(contribuicaoDTO.getPessoa());
        return contribuicaoRepository.save(contribuicao);
    }

    public Contribuicao buscarPorId(Long id){
        return contribuicaoRepository.findById(id).orElseThrow(() -> new NotFoundException("Contribuição não encontrada"));
    }

    public List<Contribuicao> listarTodos(){
        return contribuicaoRepository.findAll();
    }

    public Contribuicao atualizar(Long id, ContribuicaoDTO dto){
        Contribuicao encontrado = contribuicaoRepository.findById(id).orElseThrow(() -> new NotFoundException("Contribuição não encontrada"));
        encontrado.setPessoa(dto.getPessoa());
        encontrado.setItem(dto.getItem());

        return contribuicaoRepository.save(encontrado);
    }

    public void deletar(Long id){
        Contribuicao encontrado = contribuicaoRepository.findById(id).orElseThrow(() -> new NotFoundException("Contribuição não encontrada"));
        contribuicaoRepository.deleteById(id);
    }
}
