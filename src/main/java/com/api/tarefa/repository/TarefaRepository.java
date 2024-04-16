package com.api.tarefa.repository;

import com.api.tarefa.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    List<Tarefa> findByDataCriacao(Date dataCriacao);
    List<Tarefa> findByStatus(String status);
    List<Tarefa> findByDataVencimento(Date dataVencimento);

}
