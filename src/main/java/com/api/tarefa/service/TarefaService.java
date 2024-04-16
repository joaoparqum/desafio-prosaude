package com.api.tarefa.service;

import com.api.tarefa.model.Tarefa;
import com.api.tarefa.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    @Autowired
    TarefaRepository tarefaRepository;

    public Tarefa save(Tarefa tarefa) {
        Date dataCriacao = tarefa.getDataCriacao();
        Date dataVencimento = tarefa.getDataVencimento();

        if (dataVencimento != null && dataVencimento.before(dataCriacao)) {
            throw new IllegalArgumentException("A data de vencimento não pode ser anterior à data de criação!");
        }

        return tarefaRepository.save(tarefa);
    }

    public List<Tarefa> findAll() {
        return tarefaRepository.findAll();
    }

    public Optional<Tarefa> findById(Long id) {
        return tarefaRepository.findById(id);
    }

    public void delete(Tarefa tarefa){
        tarefaRepository.delete(tarefa);
    }

    public List<Tarefa> findByStatus(String status) {
        return tarefaRepository.findByStatus(status);
    }

    public List<Tarefa> findByDataCriacao(Date dataCriacao) {
        return tarefaRepository.findByDataCriacao(dataCriacao);
    }

    public List<Tarefa> findByDataVencimento(Date dataVencimento) {
        return tarefaRepository.findByDataVencimento(dataVencimento);
    }
}
