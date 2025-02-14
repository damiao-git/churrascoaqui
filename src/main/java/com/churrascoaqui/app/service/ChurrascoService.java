package com.churrascoaqui.app.service;

import com.churrascoaqui.app.entity.Churrasco;
import com.churrascoaqui.app.entity.ChurrascoDTO;
import com.churrascoaqui.app.exception.NotFoundException;
import com.churrascoaqui.app.repository.ChurrascoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChurrascoService {
    @Autowired
    private ChurrascoRepository repository;

    public Churrasco salvar(ChurrascoDTO churrasco) {
        Churrasco novo = new Churrasco();
        novo.setLocal(churrasco.getLocal());
        novo.setData(churrasco.getData());

        return repository.save(novo);
    }

    public List<Churrasco> listarTodos() {
        return repository.findAll(Sort.by("id"));
    }

    public
    Churrasco buscarPorId(Long id) throws Exception {
            return repository.findById(id).orElseThrow(() -> new NotFoundException("Churrasco não encontrado!"));
    }

    public Churrasco editarPorId(Long id, ChurrascoDTO data) throws Exception {

            Churrasco churrascoEncontrado = repository.findById(id).orElseThrow(() -> new NotFoundException("Churrasco não encontrado!"));

            churrascoEncontrado.setLocal(data.getLocal());
            churrascoEncontrado.setData(data.getData());
            repository.save(churrascoEncontrado);

            return churrascoEncontrado;
    }

    public void deleterPorId(Long id) throws Exception {
            Churrasco churrascoEncontrado = repository.findById(id).orElseThrow(() -> new NotFoundException("Churrasco não encontrado!"));
            repository.deleteById(id);
    }
}
