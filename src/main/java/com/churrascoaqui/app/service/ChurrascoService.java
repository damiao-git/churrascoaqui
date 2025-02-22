package com.churrascoaqui.app.service;

import com.churrascoaqui.app.entity.Churrasco;
import com.churrascoaqui.app.entity.ChurrascoDTO;
import com.churrascoaqui.app.entity.FiltroDTO;
import com.churrascoaqui.app.entity.Pessoa;
import com.churrascoaqui.app.exception.NotFoundException;
import com.churrascoaqui.app.repository.ChurrascoRepository;
import com.churrascoaqui.app.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ChurrascoService {
    @Autowired
    private ChurrascoRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;

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

    public void adicionarPessoaChurrasco(Long pessoaId, Long churrascoId){
        Churrasco churrasco = repository.findById(churrascoId).orElseThrow(() -> new NotFoundException("Churrasco não encontrado"));
        Pessoa pessoa = pessoaRepository.findById(pessoaId).orElseThrow(() -> new NotFoundException("Pessoa não encontrada"));

        churrasco.getPessoas().add(pessoa);
        repository.save(churrasco);
    }

    public List<Churrasco> filtrar(FiltroDTO filtroDTO){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String localFormatado = "%" + filtroDTO.getLocal() + "%";
        try {
            Date dataInicial = (filtroDTO.getDataInicial() != null)
                    ? filtroDTO.getDataInicial()
                    : sdf.parse("1900-01-01");

            Date dataFinal = (filtroDTO.getDataFinal() != null)
                    ? filtroDTO.getDataFinal()
                    : sdf.parse("2100-12-30");

            return repository.filtrar(dataInicial, dataFinal, localFormatado);
        } catch (ParseException e) {
            throw new RuntimeException("Erro ao converter datas", e);
        }
    }
}
