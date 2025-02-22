package com.churrascoaqui.app.controller;

import com.churrascoaqui.app.entity.*;
import com.churrascoaqui.app.exception.GlobalExceptionHandler;
import com.churrascoaqui.app.exception.NotFoundException;
import com.churrascoaqui.app.service.ChurrascoService;
import com.churrascoaqui.app.service.ViaCepService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/churrasco")
public class ChurrascoController {

    @Autowired
    private ChurrascoService churrascoService;

    @Autowired
    private ViaCepService cepService;

    @GetMapping
    @SneakyThrows
    public ResponseEntity<List<Churrasco>> listarTodos(){
        try{
            List<Churrasco> lista = churrascoService.listarTodos();
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("{id}")
    @SneakyThrows
    public ResponseEntity<Churrasco> buscarPorId(@PathVariable Long id){
        try{
            Churrasco churrasco = churrascoService.buscarPorId(id);
            return ResponseEntity.ok(churrasco);
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());
        }
        catch (Exception e){
            throw new GlobalExceptionHandler();
        }
    }

    @GetMapping("filtrar")
    public List<Churrasco> filtrar(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal,
            @RequestParam(required = false) String local) {

        Date dateI = null;
        Date dateF = null;
        String localFormatado = "";

        if (dataInicial != null) {
            LocalDateTime inicialLocalDateTime = dataInicial.atStartOfDay();
            dateI = Date.from(inicialLocalDateTime.atZone(ZoneId.systemDefault()).toInstant());
        }

        if (dataFinal != null) {
            LocalDateTime finalLocalDateTime = dataFinal.atTime(23, 59, 59, 999_000_000);
            dateF = Date.from(finalLocalDateTime.atZone(ZoneId.systemDefault()).toInstant());
        }
        if(local != null){
            localFormatado = local;
        }

        FiltroDTO filtroDTO = new FiltroDTO();
        filtroDTO.setDataInicial(dateI);
        filtroDTO.setDataFinal(dateF);
        filtroDTO.setLocal(localFormatado);

        return churrascoService.filtrar(filtroDTO);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Churrasco> salvar(@Valid @RequestBody ChurrascoDTO dados){

            Churrasco salvo =  churrascoService.salvar(dados);
            return ResponseEntity.ok(salvo);

    }

    @Transactional
    @SneakyThrows
    @PutMapping("{id}")
    public ResponseEntity<Churrasco> atualizar(@PathVariable Long id, @Valid @RequestBody ChurrascoDTO dados){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(churrascoService.editarPorId(id, dados));
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getMessage());

        } catch (Exception e) {
            throw new GlobalExceptionHandler();
        }
    }

    @Transactional
    @SneakyThrows
    @DeleteMapping("{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id){
        try {
            churrascoService.deleterPorId(id);
            return ResponseEntity.ok("Churrasco " + id + " apagado com sucesso");
        }
        catch (NotFoundException n){
            throw new NotFoundException(n.getMessage());
        }
        catch (Exception e) {
            throw new GlobalExceptionHandler();
        }
    }

    @GetMapping("teste")
    public ResponseEntity<?> testecep(@RequestBody ViaCepResponseDTO dto){
        return ResponseEntity.ok(cepService.verificarCep(dto.getCep()));
    }

    @PostMapping("adicionarPessoaChurrasco")
    public ResponseEntity<?> adicionarPessoaChurrasco(@RequestBody AdicionarPessoaChurrasco dto){
        churrascoService.adicionarPessoaChurrasco(dto.getPessoaId(), dto.getChurrascoId());
        return ResponseEntity.status(HttpStatus.OK).body("Pessoa adicionada ao churrasco");
    }

}
