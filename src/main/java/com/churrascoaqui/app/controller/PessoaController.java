package com.churrascoaqui.app.controller;

import com.churrascoaqui.app.entity.Pessoa;
import com.churrascoaqui.app.entity.PessoaDTO;
import com.churrascoaqui.app.service.PessoaService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("api/pessoa")
public class PessoaController {
    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    @SneakyThrows
    public ResponseEntity<List<Pessoa>> listarTodos(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(pessoaService.listarTodos());
        }
        catch(Exception e){
            throw new Exception(e);
        }
    }

    @GetMapping("{id}")
    @SneakyThrows
    public ResponseEntity<Pessoa> buscarPorId(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(pessoaService.buscarPorId(id));
        }
        catch (Exception e){
            throw new Exception(e);
        }
    }

    @PostMapping
    @SneakyThrows
    public ResponseEntity<Pessoa> salvar(@RequestBody @Valid PessoaDTO pessoaDTO){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(pessoaService.salvar(pessoaDTO));
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @PutMapping("{id}")
    @SneakyThrows
    public ResponseEntity<Pessoa> atualizar(@PathVariable Long id, @RequestBody PessoaDTO pessoaDTO){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(pessoaService.atualizar(id, pessoaDTO));
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletar(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body("Pessoa deletada");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
