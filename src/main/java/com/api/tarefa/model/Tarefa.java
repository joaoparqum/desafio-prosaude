package com.api.tarefa.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tarefa")
@Data
public class Tarefa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String titulo;

    @NotNull
    private String descricao;

    @Column(name = "dataCriacao")
    @Temporal(TemporalType.DATE)
    private Date dataCriacao;

    @NotNull
    @Column(name = "dataVencimento")
    @Temporal(TemporalType.DATE)
    private Date dataVencimento;

    private String status = "pendente";

    public Tarefa() {
        this.status = "pendente";
        this.dataCriacao = new Date();
    }
}
