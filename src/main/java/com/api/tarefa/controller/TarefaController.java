package com.api.tarefa.controller;

import com.api.tarefa.dto.TarefaDTO;
import com.api.tarefa.model.Tarefa;
import com.api.tarefa.service.TarefaService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/tarefas")
public class TarefaController {

    final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @PostMapping
    public ResponseEntity<Object> saveTarefa(@RequestBody @Valid TarefaDTO tarefaDto){
        Tarefa tarefa = new Tarefa();
        BeanUtils.copyProperties(tarefaDto, tarefa);
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaService.save(tarefa));
    }

    @GetMapping
    public ResponseEntity<List<Tarefa>> getAllTarefa(){
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneTarefa(@PathVariable(value = "id") Long id){
        Optional<Tarefa> tarefa = tarefaService.findById(id);
        if (!tarefa.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada!.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(tarefa.get());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<Object> getTarefaByStatus(@PathVariable(value = "status") String status){
        List<Tarefa> tarefa = tarefaService.findByStatus(status);
        if (tarefa.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada!.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(tarefa);
    }

    @GetMapping("/dataCriacao")
    public ResponseEntity<Object> getTarefaByDataCriacao(@RequestParam(value = "dataCriacao") @DateTimeFormat(pattern="yyyy-MM-dd") Date dataCriacao) {
        List<Tarefa> tarefa = tarefaService.findByDataCriacao(dataCriacao);
        if (tarefa.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada!.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(tarefa);
    }

    @GetMapping("/dataVencimento")
    public ResponseEntity<Object> getTarefaByDataVencimento(@RequestParam(value = "dataVencimento") @DateTimeFormat(pattern="yyyy-MM-dd") Date dataVencimento){
        List<Tarefa> tarefa = tarefaService.findByDataVencimento(dataVencimento);
        if (tarefa.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada!.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(tarefa);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTarefa(@PathVariable(value = "id") Long id){
        Optional<Tarefa> tarefaOptional = tarefaService.findById(id);
        if (!tarefaOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada!");
        }
        tarefaService.delete(tarefaOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Tarefa deletada com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTarefa(@PathVariable(value = "id") Long id,
                                                   @RequestBody @Valid TarefaDTO tarefaDto){
        Optional<Tarefa> tarefaOptional = tarefaService.findById(id);
        if (!tarefaOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada!");
        }
        Tarefa tarefa = new Tarefa();
        BeanUtils.copyProperties(tarefaDto, tarefa);
        tarefa.setId(tarefaOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(tarefaService.save(tarefa));
    }


}
